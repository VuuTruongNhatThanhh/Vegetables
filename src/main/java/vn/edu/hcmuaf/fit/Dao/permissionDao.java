package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.services.SendingEmail;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public static void main(String[] args) {
        permissionDao pd = new permissionDao();
        pd.addDB("1","tk10",2);
    }
}
