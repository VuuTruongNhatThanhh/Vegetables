package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.TypeProduct;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.SendingEmail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TypeProductDao {
    public static TypeProductDao instance;

    public TypeProductDao() {
    }

    public static TypeProductDao getInstance() {
        if (instance == null) instance = new TypeProductDao();
        return instance;
    }

    public List<TypeProduct> getAll() {
        List<TypeProduct> result = new LinkedList<>();
        String query = "SELECT MALSP, TENLSP, PHANLOAICHA FROM loaisp";
        try {
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new TypeProduct(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<TypeProduct> getTypeChild() {
        List<TypeProduct> result = new LinkedList<>();
        String query = "SELECT MALSP, TENLSP, PHANLOAICHA FROM loaisp where PHANLOAICHA is not null";
        try {
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new TypeProduct(rs.getString(1), rs.getString(2)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<TypeProduct> getParentType() {
        List<TypeProduct> result = new LinkedList<>();
        String query = "SELECT MALSP, TENLSP, PHANLOAICHA FROM loaisp WHERE PHANLOAICHA is NULL";
        try {
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new TypeProduct(rs.getString(1), rs.getString(2)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<TypeProduct> getChildType(String id) {
        List<TypeProduct> result = new LinkedList<>();
        String query = "SELECT MALSP, TENLSP, PHANLOAICHA FROM loaisp WHERE PHANLOAICHA = ?";
        try {
            PreparedStatement ps = DBConnect.getInstance().get(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new TypeProduct(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String getType(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select TENLSP from loaisp where MALSP = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
                rs.close();
                ps.close();
                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public String getNewId() {
        List<TypeProduct> list = TypeProductDao.getInstance().getAll();
        int id = 0;
        for (TypeProduct u : list) {
            int maxId = Integer.parseInt(u.getId().substring(u.getId().indexOf("P") + 1));
            if (maxId > id) {
                id = maxId;
            }
        }
        return "LSP" + (id + 1);
    }

    public String addDB(String name, String typeFather) {
        String id = getNewId();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("insert into loaisp(MALSP, TENLSP, PHANLOAICHA) values (?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, typeFather);

            int i = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public void update(String id, String name, String typeFather) {
        PreparedStatement ps = DBConnect.getInstance().get("UPDATE loaisp set TENLSP = ?, PHANLOAICHA = ? where MALSP = ?");
        try {
            ps.setString(1, name);
            ps.setString(2, typeFather);
            ps.setString(3, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from loaisp where MALSP = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
