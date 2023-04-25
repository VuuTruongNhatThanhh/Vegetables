package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.TypeProduct;
import vn.edu.hcmuaf.fit.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LogDao {
    public static LogDao instance;

    public LogDao() {
    }
    public static LogDao getInstance() {
        if (instance == null) {
            instance = new LogDao();
        }
        return instance;
    }
    public List<Log> getAll() {
        List<Log> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, levell, userr, ip, src, content, createAt, status from log");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Log(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(8)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Log> getNullUser() {
        List<Log> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, levell, userr, ip, src, content, createAt, status from log where log.userr IS NULL ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Log(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(8)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public List<Log> getWrongUser() {
        List<Log> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, levell, userr, ip, src, content, createAt, status from log where log.src=\"LOGIN FAILED\"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Log(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(8)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }



    public String NumberAccessMod(int id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select count(*) from log JOIN permission ON log.userr = permission.u_id where permission.rs_id =1 AND permission.per = ?");
            ps.setInt(1, id);
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

    public void delete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from log where id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteUser(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from log where userr = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteAll() {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from log");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
