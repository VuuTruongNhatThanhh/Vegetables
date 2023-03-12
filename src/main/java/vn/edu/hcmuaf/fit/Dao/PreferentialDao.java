package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.Preferential;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
