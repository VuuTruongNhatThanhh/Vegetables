package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.Preferential;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PreferentialDao {
    public static PreferentialDao instance;

    public PreferentialDao() {

    }

    public static PreferentialDao getInstance() {
        if (instance == null)
            instance = new PreferentialDao();
        return instance;
    }

    public Preferential get(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAUD, TIENUD, NGAYBD, NGAYKT from uudai where MAUD = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Preferential p = new Preferential(rs.getString(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4));
                rs.close();
                ps.close();
                return p;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Preferential> getAll() {
        List<Preferential> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAUD, TIENUD, NGAYBD, NGAYKT from uudai");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Preferential(rs.getString(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public String addDB(String id, String price, String dayStart, String dayEnd) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("insert into uudai(MAUD, TIENUD, NGAYBD, NGAYKT) values (?,?,?,?)");
            ps.setString(1, id);
            ps.setString(2, price);
            ps.setString(3, dayStart);
            ps.setString(4, dayEnd);

            int i = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public String niceDayStart(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT DATE_FORMAT(NGAYBD, \"%d/%m/%Y \") FROM uudai WHERE MAUD=?");
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

    public String niceDayEnd(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT DATE_FORMAT(NGAYKT, \"%d/%m/%Y \") FROM uudai WHERE MAUD=?");
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

    public void update(String id, String price, String dayStart, String dayEnd) {
        PreparedStatement ps = DBConnect.getInstance().get("UPDATE uudai set MAUD = ?, TIENUD = ?, NGAYBD = ?, NGAYKT = ?  where MAUD = ?");
        try {
            ps.setString(1, id);
            ps.setString(2, price);
            ps.setString(3, dayStart);
            ps.setString(4, dayEnd);
            ps.setString(5, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from uudai where MAUD = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
