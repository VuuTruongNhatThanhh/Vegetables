package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.services.API_LOGISTIC.Transport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportDao {
    public static TransportDao instance;
    public TransportDao() {
    }
    public static TransportDao getInstance() {
        if (instance == null) {
            instance = new TransportDao();
        }
        return instance;
    }
    public String addTransport(Transport transport, String idbill) {
        String id ="";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("insert into transports(id, id_bill, created_at, leadtime) values (?,?,?,?)");

            ps.setString(1, transport.getId());
            ps.setString(2, idbill);
            ps.setString(3, transport.getCreated_at());
            ps.setString(4, transport.getLeadTime());
            int i = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public String selectIdTransport(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT id from transports WHERE id_bill = ?");
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

    public String createdAtTransport(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT created_at from transports WHERE id_bill = ?");
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

    public String leaTimeTransport(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT leadtime from transports WHERE id_bill = ?");
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
