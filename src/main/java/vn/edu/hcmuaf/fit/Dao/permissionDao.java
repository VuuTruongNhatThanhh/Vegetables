package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.bean.Permission;
import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.TypeProduct;
import vn.edu.hcmuaf.fit.services.SendingEmail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class permissionDao {
    public static permissionDao instance;
    public permissionDao() {
    }
    public static permissionDao getInstance() {
        if (instance == null) {
            instance = new permissionDao();
        }
        return instance;
    }
    public String addDB(String rs_id, String u_id, int per) {
        String id ="";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("insert into permission(rs_id, u_id, per) values (?,?,?)");

            ps.setString(1, rs_id);
            ps.setString(2, u_id);
            ps.setInt(3, per);
            int i = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public List<Permission> getAll() {
        List<Permission> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, rs_id, u_id, per from permission");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Permission(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public Permission getPermissById(String id) {

        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT id, rs_id, u_id, per FROM permission WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Permission p = new Permission(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                rs.close();
                ps.close();
                return p;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String selectrolename(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT taikhoan.PHANQUYEN from taikhoan WHERE MATK = ?");
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

    public void update(String id, String per) {
        PreparedStatement ps = DBConnect.getInstance().get("UPDATE permission set permission.per = ? where permission.id = ?");
        try {
            ps.setString(1, per);
            ps.setString(2, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        permissionDao pd = new permissionDao();
//        pd.addDB("1","tk10",2);
        pd.update("67","2");
    }
}
