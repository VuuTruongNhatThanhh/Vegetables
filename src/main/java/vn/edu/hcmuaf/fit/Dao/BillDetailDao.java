package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.BillDetails;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.CartDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BillDetailDao {
    public static BillDetailDao instance;

    public BillDetailDao() {
    }

    public static BillDetailDao getInstance() {
        if (instance == null)
            instance = new BillDetailDao();
        return instance;
    }

    public void addDB(Collection<CartDetails> list, String idBill) {
        PreparedStatement ps = DBConnect.getInstance().get("insert into CTHD values (?, ?, ?, ?, ?)");
        try {
            for (CartDetails cd : list) {
                ps.setString(1, idBill);
                ps.setString(2, cd.getProduct().getId());
                ps.setString(3, cd.getWeight().getId());
                ps.setInt(4, cd.getQuanity());
                ps.setDouble(5, cd.getPrice());
                ps.executeUpdate();
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int getAmount(String id, String idW) {
        int res = 0;
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select sum(SL) from cthd where MASP = ? and MAKL = ?");
            ps.setString(1, id);
            ps.setString(2, idW);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                res = rs.getInt(1);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<BillDetails> getById(String id) {
        List<BillDetails> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, MASP, MAKL, SL, THANHTIEN from cthd where MAHD = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                res.add(new BillDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public void delete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from cthd where MAHD = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<BillDetails> getAll(String id) {
        List<BillDetails> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, MASP, MAKL, SL, THANHTIEN from cthd JOIN hoadon ON cthd.MAHD = hoadon.MAHD where MAHD = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new BillDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public String NameProduct(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT sanpham.TENSP FROM sanpham JOIN cthd ON sanpham.MASP = cthd.MASP WHERE cthd.MASP = ?");
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
    public String NameWeight(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT khoiluong.KL FROM khoiluong JOIN cthd ON khoiluong.MAKL = cthd.MAKL WHERE cthd.MAKL = ?");
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




    public static void main(String[] args) {

    }

}
