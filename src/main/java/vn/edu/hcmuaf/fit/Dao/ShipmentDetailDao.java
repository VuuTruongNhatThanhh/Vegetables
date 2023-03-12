package vn.edu.hcmuaf.fit.Dao;


import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.ShipmentDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentDetailDao {
    public static ShipmentDetailDao instance;

    public ShipmentDetailDao() {
    }

    public static ShipmentDetailDao getInstance() {
        if (instance == null)
            instance = new ShipmentDetailDao();
        return instance;
    }

    public ShipmentDetails get(String idU) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MATT, HOTENNHAN, SDT, TINH, QUANHUYEN, XAPHUONG, DIACHI,MATK from ttgiaohang where MATK = ? and SUDUNG = 1");
            ps.setString(1, idU);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ShipmentDetails res = new ShipmentDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                rs.close();
                ps.close();
                return res;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addDB(String name, String phone, String province, String district, String ward, String address, String idU) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("INSERT INTO ttgiaohang (HOTENNHAN,SDT, TINH, QUANHUYEN, XAPHUONG, DIACHI, SUDUNG,MATK) VALUES(?,?,?,?,?,?,0,?)");
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, province);
            ps.setString(4, district);
            ps.setString(5, ward);
            ps.setString(6, address);
            ps.setString(7, idU);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from ttgiaohang where MATK = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
