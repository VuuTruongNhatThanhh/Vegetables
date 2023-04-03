package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.CartDetails;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.Weight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class WeightDao {
    public static WeightDao instance;

    public WeightDao() {
    }

    public static WeightDao getInstance() {
        if (instance == null) instance = new WeightDao();
        return instance;
    }

    public List<Weight> getByIdProduct(String id) {
        List<Weight> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAKL, KL, GIA_BAN, SL from khoiluong where khoiluong.MASP = ? order by khoiluong.KL ASC");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Weight(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public Weight getWeightById(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MAKL, KL, GIA_BAN, SL from khoiluong where MAKL = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Weight w = new Weight(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                rs.close();
                ps.close();
                return w;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateAmount(Collection<CartDetails> list) {
        PreparedStatement ps = DBConnect.getInstance().get("update khoiluong set SL = ?  where MAKL = ?");
        try {
            for (CartDetails cd : list) {
                ps.setInt(1, cd.setAmount());
                ps.setString(2, cd.getWeight().getId());
                ps.executeUpdate();
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNewId() {
        try {
            int max = 0;
            PreparedStatement ps = DBConnect.getInstance().get("select MAKL from khoiluong");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                int num = Integer.parseInt(id.substring(id.indexOf("L") + 1));
                max = max < num ? num : max;
            }
            ps.close();
            return "KL" + (max + 1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addDB(String id, int weight, int amount, int price, String idP) {
        PreparedStatement ps = DBConnect.getInstance().get("insert into khoiluong values (?,?,?,?,?)");
        try {
            ps.setString(1, id);
            ps.setInt(2, weight);
            ps.setInt(3, price);
            ps.setInt(4, amount);
            ps.setString(5, idP);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from khoiluong where MAKL = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String id, int weight, int amount, int price) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("update khoiluong set KL = ?, GIA_BAN = ?, SL = ? where MAKL = ?");
            ps.setInt(1, weight);
            ps.setInt(2, price);
            ps.setInt(3, amount);
            ps.setString(4, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String selectWeight(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT KL FROM khoiluong WHERE MAKL = ?");
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
