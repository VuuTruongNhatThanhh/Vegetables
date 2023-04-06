package vn.edu.hcmuaf.fit.Dao;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.xdevapi.Session;
import com.sun.jdi.connect.spi.Connection;
import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.SHA1;
import vn.edu.hcmuaf.fit.services.SendingEmail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.beans.Statement;
import java.net.PasswordAuthentication;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class UserDao {
    public static UserDao instance;

    public UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }


    public List<User> getAll() {
        List<User> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MATK, EMAIL, MK, HOTEN, PHANQUYEN, HASH, ACTIVATE, DATE_ADD_HASH from taikhoan");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String getNewId() {
        List<User> list = UserDao.getInstance().getAll();
        int id = 0;
        for (User u : list) {
            int maxId = Integer.parseInt(u.getId().substring(u.getId().indexOf("K") + 1));
            if (maxId > id) {
                id = maxId;
            }
        }
        return "TK" + (id + 1);
    }

    public String addDB(String email, String pass, String uname, int role, String hash) {
        String id = getNewId();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("insert into taikhoan(MATK, EMAIL, MK, HOTEN, PHANQUYEN, HASH, DATE_ADD_HASH) values (?,?,?,?,?,?,NOW())");
            ps.setString(1, id);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, uname);
            ps.setInt(5, role);
            ps.setString(6, hash);
            int i = ps.executeUpdate();
            if(i!=0){
                SendingEmail se = new SendingEmail(email, hash);
                se.sendMail();
                        return "SUCCESS";

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


    public void update(User user) {

    }

    public void delete(String id) {
        ShipmentDetailDao.getInstance().delete(id);
        BillDao.getInstance().deleteByIdUser(id);
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from taikhoan where MATK = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean checkEmail(String email) {
        List<User> users = getAll();
        for (User u : users)
            if (email.equals(u.getEmail())) return true;
        return false;
    }


    public User checkLogin(String email, String pass) {
        List<User> users = getAll();
        for (User u : users)

            if (email.equals(u.getEmail()) && SHA1.hashPassword(pass).equals(u.getPassword())) return u;
        return null;
    }

    public User checkPermission(int roleId) {

        return null;
    }

    public String getNameById(String id) {
        String res = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select HOTEN from taikhoan where MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) res = rs.getString(1);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public boolean check(String id, String currentpass) {
        PreparedStatement st = DBConnect.getInstance().get("select MK from taikhoan where MATK = ?");
        try {
            st.setString(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                return currentpass.equals(rs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void changePassword(String id, String password) {
        try {
            PreparedStatement st = DBConnect.getInstance().get("update taikhoan set MK = ? where MATK = ?");
            st.setString(1, password);
            st.setString(2, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String selectemail(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT EMAIL from taikhoan WHERE MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
                rs.close();
                ps.close();
                return result;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public String selectrolename(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT PHANQUYEN from taikhoan WHERE MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
                rs.close();
                ps.close();
                return result;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }



}
