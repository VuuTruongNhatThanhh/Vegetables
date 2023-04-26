package vn.edu.hcmuaf.fit.Dao;


import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.ShipmentDetails;
import vn.edu.hcmuaf.fit.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
            PreparedStatement ps = DBConnect.getInstance().get("select id, info_deliver.`name`, phone, province, district, ward, address ,id_user from info_deliver where id_user = ? and info_deliver.`use` = 1");
            ps.setString(1, idU);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ShipmentDetails res = new ShipmentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                rs.close();
                ps.close();
                return res;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<ShipmentDetails> getAll() {
        List<ShipmentDetails> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, info_deliver.`name`, phone, province, district, ward, address, info_deliver.`use`, id_user from info_deliver");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new ShipmentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public String getNewId() {
        List<ShipmentDetails> list = ShipmentDetailDao.getInstance().getAll();
        int id = 0;
        for (ShipmentDetails u : list) {
            int maxId = Integer.parseInt(u.getId().substring(u.getId().indexOf("T") + 2));
            if (maxId > id) {
                id = maxId;
            }
        }
        return "TT" + (id + 2);
    }

    public String addDB(String name, String phone, String province, String district, String ward, String address, String idU) {
        String id = getNewId();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("INSERT INTO info_deliver (id,info_deliver.`name`,phone, province, district, ward, address, info_deliver.`use`,id_user) VALUES(?,?,?,?,?,?,?,0,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, province);
            ps.setString(5, district);
            ps.setString(6, ward);
            ps.setString(7, address);
            ps.setString(8, idU);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;

    }
    public String addDB2(String name, String phone, String province, String district, String ward, String address, String idU) {
        String id = getNewId();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("INSERT INTO info_deliver (id,info_deliver.`name`,phone, province, district, ward, address, info_deliver.`use`,id_user) VALUES(?,?,?,?,?,?,?,1,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, province);
            ps.setString(5, district);
            ps.setString(6, ward);
            ps.setString(7, address);
            ps.setString(8, idU);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;

    }
    public void deleteOldInfo(String idu) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from info_deliver where id_user = ? AND info_deliver.`use` = 1 ");
            ps.setString(1, idu);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from info_deliver where id_user = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String NameUser(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT info_deliver.`name` FROM info_deliver JOIN bill ON info_deliver.id = bill.id_info WHERE bill.id_info = ?");
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
    public String PhoneUser(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT info_deliver.phone FROM info_deliver JOIN bill ON info_deliver.id = bill.id_info WHERE bill.id_info = ?");
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
    public String AddressUser(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT info_deliver.address FROM info_deliver JOIN bill ON info_deliver.id = bill.id_info WHERE bill.id_info = ?");
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
    public String ProvinceUser(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT info_deliver.province FROM info_deliver JOIN bill ON info_deliver.id = bill.id_info WHERE bill.id_info = ?");
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
    public String DistrictUser(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT info_deliver.district FROM info_deliver JOIN bill ON info_deliver.id = bill.id_info WHERE bill.id_info = ?");
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
    public String WardUser(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT info_deliver.ward FROM info_deliver JOIN bill ON info_deliver.id = bill.id_info WHERE bill.id_info = ?");
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
    public void update(String uid, String name, String phone, String province, String district, String ward, String address) {
        PreparedStatement ps = DBConnect.getInstance().get("UPDATE info_deliver set info_deliver.`name` = ?, phone = ?, province = ?, district = ?, ward = ?, address = ? where id_user = ? AND info_deliver.`use` = 1 ");
        try {
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, province);
            ps.setString(4, district);
            ps.setString(5, ward);
            ps.setString(6, address);
            ps.setString(7, uid);


            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        ShipmentDetailDao ss = new ShipmentDetailDao();
//        System.out.println(ss.getNewId());
        ss.update("TT2","thanhh","0919588665","DN","Bien Hoa","Tam Hiep","451/15");
    }
}
