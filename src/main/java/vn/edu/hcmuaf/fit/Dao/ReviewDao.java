package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReviewDao {
    public static ReviewDao instance;

    public ReviewDao() {

    }

    public static ReviewDao getInstance() {
        if (instance == null) instance = new ReviewDao();
        return instance;
    }

    public List<Review> getAll(String idP) {
        List<Review> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id_product, id_user, date, content, star from review where id_product = ? ORDER BY date DESC");
            ps.setString(1, idP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Review(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Review> getAllNull() {
        List<Review> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id_product, id_user, date, content, star from review");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Review(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Review> getReview(String idP, int amount) {
        List<Review> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select id_product, id_user,date, content, star from review where id_product = ? ORDER BY date DESC limit 0, ?");
            ps.setString(1, idP);
            ps.setInt(2, amount + 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Review(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getStar(String idP) {
        int res = 0;
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select AVG(star) from review where id_product = ?");
            ps.setString(1, idP);
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

    public int amountReview(String idP) {
        int res = 0;
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select count(*) from review where id_product = ?");
            ps.setString(1, idP);
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

    public void addReview(String idP, String idU, String mess, int star) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("insert into review values(?,?, CURDATE(), ?,?)");
            ps.setString(1, idP);
            ps.setString(2, idU);
            ps.setString(3, mess);
            ps.setInt(4, star);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String idP, String idU) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from review where id_product = ? AND id_user = ?");
            ps.setString(1, idP);
            ps.setString(2, idU);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public String selectContent(String idP, String idU) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT content FROM review WHERE id_product = ? AND id_user = ?");
            ps.setString(1, idP);
            ps.setString(2, idU);
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
    public int selectStar(String idP, String idU) {
        int result = 0;
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT star FROM review WHERE id_product = ? AND id_user = ?");
            ps.setString(1, idP);
            ps.setString(2, idU);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
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
