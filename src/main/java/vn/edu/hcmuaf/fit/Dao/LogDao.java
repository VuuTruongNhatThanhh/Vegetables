package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LogDao {
    public static LogDao instance;

    public LogDao() {
    }
    public static LogDao getInstance() {
        if (instance == null) {
            instance = new LogDao();
        }
        return instance;
    }
    public List<Log> getAll() {
        List<Log> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id, levell, userr, ip, src, content, createAt, status from log");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Log(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(8)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

}
