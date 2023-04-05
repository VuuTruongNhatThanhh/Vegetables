package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.BillDetails;
import vn.edu.hcmuaf.fit.model.Bills;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BillDao {
    public static BillDao instance;

    public static BillDao getInstance() {
        if (instance == null) instance = new BillDao();
        return instance;
    }

    public List<Bills> getAll() {
        List<Bills> res = new LinkedList<>();

        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK from hoadon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String addDB(double total, String idU, String idinfo) {
        List<Bills> list = getAll();
        int id = 0;
        for (Bills b : list) {
            int maxId = Integer.parseInt(b.getId().substring(b.getId().indexOf("D") + 1));
            id = id < maxId ? maxId : id;
        }
        try {
            PreparedStatement ps = DBConnect.getInstance().get("INSERT INTO hoadon VALUES(?, CURDATE(), ?, 0, ?, ?)");
            ps.setString(1, "HD" + (id + 1));
            ps.setDouble(2, total);
            ps.setString(3, idU);
            ps.setString(4, idinfo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "HD" + (id + 1);
    }

    public int totalBill(int dateRange) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT count(*)  FROM hoadon WHERE DATEDIFF(Date(NOW()), NGHD) <= ?");
            ps.setInt(1, dateRange);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int res = rs.getInt(1);
            rs.close();
            ps.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int totalInCome(int dateRange) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT sum(TONGTIEN) from hoadon where TRANGTHAI = 2 and DATEDIFF(Date(NOW()), NGHD) <= ?");
            ps.setInt(1, dateRange);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int res = rs.getInt(1);
            rs.close();
            ps.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int totalBillCancelled(int dateRange) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT count(*) from hoadon where TRANGTHAI = 3 and DATEDIFF(Date(NOW()), NGHD) <= ?");
            ps.setInt(1, dateRange);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int res = rs.getInt(1);
            rs.close();
            ps.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bills> RecentBill() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK, MATT from hoadon where TRANGTHAI = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Bills> CancelBill() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK, MATT from hoadon where TRANGTHAI = 3");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Bills> ConfirmBill() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK, MATT from hoadon where TRANGTHAI = 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public List<Bills> ShipBill() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK, MATT from hoadon where TRANGTHAI = 5");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public List<Bills> DeliverBill() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK, MATT from hoadon where TRANGTHAI = 2");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public List<Bills> MoveToShipper() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK, MATT from hoadon where TRANGTHAI = 5");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }



    public void confirm(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("update hoadon set TRANGTHAI = 1 where MAHD = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void ship(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("update hoadon set TRANGTHAI = 5 where MAHD = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(String id) {
        try {
            BillDetailDao.getInstance().delete(id);
            PreparedStatement ps = DBConnect.getInstance().get("delete from hoadon where MAHD = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Bills> ConfirmBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK from hoadon where TRANGTHAI = 1 and MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Bills> deliveredBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK from hoadon where TRANGTHAI = 2 and MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Bills> CancelBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK from hoadon where TRANGTHAI = 5 and MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public List<Bills> ShipBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK from hoadon where TRANGTHAI = 5 and MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Bills> RecentBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK, MATT from hoadon where TRANGTHAI = 0 and MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public void delivered(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("update hoadon set TRANGTHAI = 2 where MAHD = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancel(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("update hoadon set TRANGTHAI = 3 where MAHD = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByIdUser(String id) {
        List<Bills> lists = BillDao.getInstance().getById(id);
        for (Bills b : lists)
            delete(b.getId());
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from hoadon where MATK = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Bills> getById(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAHD, NGHD, TONGTIEN, TRANGTHAI, MATK from hoadon where MATK = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Bills(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String totalPrice(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select TONGTIEN-15000 from hoadon where MAHD = ?");
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
