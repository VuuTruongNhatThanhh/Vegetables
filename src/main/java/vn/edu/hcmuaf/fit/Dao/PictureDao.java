package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.Pictures;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PictureDao {
    public static PictureDao instance;

    public PictureDao() {
    }

    public static PictureDao getInstance() {
        if (instance == null) instance = new PictureDao();
        return instance;
    }

    public List<Pictures> getByIdProduct(String id) {
        List<Pictures> res = new ArrayList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAANH, URL from anh where anh.MASP = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Pictures(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public void add(String url, String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("INSERT INTO anh (URL, MASP) VALUES ( ?, ?)");
            ps.setString(1, url);
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from anh where MAANH = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
