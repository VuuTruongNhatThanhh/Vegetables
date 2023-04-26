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
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user from bill");
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
            PreparedStatement ps = DBConnect.getInstance().get("INSERT INTO bill VALUES(?, CURDATE(), ?, 0, ?, ?)");
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
            PreparedStatement ps = DBConnect.getInstance().get("SELECT count(*)  FROM bill WHERE DATEDIFF(Date(NOW()), date) <= ?");
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
            PreparedStatement ps = DBConnect.getInstance().get("SELECT sum(total) from bill where status = 2 and DATEDIFF(Date(NOW()), date) <= ?");
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
    public int totalInComeMonth(int dateRange, int year) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT sum(total) from bill where status = 2 AND MONTH(date) = ? AND YEAR(date) = ?");
            ps.setInt(1, dateRange);
            ps.setInt(2, year);
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
            PreparedStatement ps = DBConnect.getInstance().get("SELECT count(*) from bill where status = 3 and DATEDIFF(Date(NOW()), date) <= ?");
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
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 0");
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
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 3");
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
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 1");
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
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 5");
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
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 2");
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
    public List<Bills> DeliverBillDay() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 2 AND DATEDIFF(Date(NOW()), date) <= 0 ");
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
    public List<Bills> DeliverBillWeek() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 2 AND DATEDIFF(Date(NOW()), date) <= 7 ");
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
    public List<Bills> DeliverBillMonth() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 2 AND DATEDIFF(Date(NOW()), date) <= 30 ");
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
    public List<Bills> DeliverBillQuater() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 2 AND DATEDIFF(Date(NOW()), date) <= 90 ");
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
    public List<Bills> DeliverBillYear() {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 2 AND DATEDIFF(Date(NOW()), date) <= 365 ");
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
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 5");
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
            PreparedStatement ps = DBConnect.getInstance().get("update bill set status = 1 where id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void ship(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("update bill set status = 5 where id = ?");
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
            PreparedStatement ps = DBConnect.getInstance().get("delete from bill where id = ?");
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
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 1 and id_user = ?");
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

    public List<Bills> deliveredBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 2 and id_user = ?");
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

    public List<Bills> CancelBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 3 and id_user = ?");
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
    public List<Bills> ShipBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 5 and id_user = ?");
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

    public List<Bills> RecentBill(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user, id_info from bill where status = 0 and id_user = ?");
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
            PreparedStatement ps = DBConnect.getInstance().get("update bill set status = 2 where id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancel(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("update bill set status = 3 where id = ?");
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
            PreparedStatement ps = DBConnect.getInstance().get("delete from bill where id_user = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void deleteByUserId(String id) {
//        try {
//            PreparedStatement ps = DBConnect.getInstance().get("delete from hoadon where MATK = ?");
//            ps.setString(1, id);
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }

    private List<Bills> getById(String id) {
        List<Bills> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, date, total, status, id_user from bill where id_user = ?");
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
            PreparedStatement ps = DBConnect.getInstance().get("select total-15000 from bill where id = ?");
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
