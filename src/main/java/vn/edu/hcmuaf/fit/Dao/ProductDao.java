package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.TypeProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ProductDao {
    public static ProductDao instance;

    public ProductDao() {
    }

    public static ProductDao getInstance() {
        if (instance == null) instance = new ProductDao();
        return instance;
    }

    public List<Product> getAll() {
        List<Product> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, MALSP from sanpham");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7));
                p.setPics(PictureDao.getInstance().getByIdProduct(p.getId()));
                p.setWeights(WeightDao.getInstance().getByIdProduct(p.getId()));
                res.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getNum(String id) {
        for (int i = 0; i < id.length(); i++) {
            if (Character.isDigit(id.charAt(i)) && id.charAt(i) != '0') {
                return Integer.parseInt(id.substring(i, id.length()));
            }
        }
        return 0;
    }

    public String newId(String item) {
        for (int i = 0; i < item.length(); i++) {
            if (Character.isDigit(item.charAt(i)) && item.charAt(i) != '0') {
                return item.substring(0, item.indexOf(item.charAt(i), i));
            }
        }
        return null;
    }

    public String createId() {
        List<Product> list = getAll();
        if (!list.isEmpty()) {
            Comparator<Product> cmp = (p1, p2) -> {
                return ProductDao.getInstance().getNum(p2.getId()) - ProductDao.getInstance().getNum(p1.getId());
            };
            Collections.sort(list, cmp);

            return newId(list.get(0).getId()) + (getNum(list.get(0).getId()) + 1);
        } else {
            return "SP00001";
        }
    }

    public void addDB(String id, String name, double discount, String dicription, String idType) {
        PreparedStatement ps = DBConnect.getInstance().get("insert into sanpham values (?,?,?,?, CURDATE(), 1, ?)");
        try {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setDouble(3, discount);
            ps.setString(4, dicription);
            ps.setString(5, idType);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(String id, String name, double discount, String dicription, String idType) {
        PreparedStatement ps = DBConnect.getInstance().get("UPDATE sanpham set TENSP = ?, DISCOUNT = ?, NGAYTHEM = CURDATE(), MOTA = ?, MALSP = ? where MASP = ?");
        try {
            ps.setString(1, name);
            ps.setDouble(2, discount);
            ps.setString(3, dicription);
            ps.setString(4, idType);
            ps.setString(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("delete from sanpham where MASP = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            setIsDelete(id);
        }
    }

    public void setIsDelete(String id) {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("update sanpham set TINHTRANG = 0 where MASP = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalProduct(String idType) {
        int res = 0;
        try {
            PreparedStatement ps = null;
            if (idType == null) {
                ps = DBConnect.getInstance().get("select count(*) from sanpham where TINHTRANG = 1");
            } else {
                res += totalProductParentType(idType);
                ps = DBConnect.getInstance().get("select count(*) from sanpham where sanpham.MALSP = ? and TINHTRANG = 1");
                ps.setString(1, idType);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res += rs.getInt(1);
                rs.close();
                ps.close();
                return res;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int totalProductParentType(String id) {
        int res = 0;
        try {
            for (TypeProduct t : TypeProductDao.instance.getChildType(id)) {
                PreparedStatement ps = DBConnect.getInstance().get("select count(*) from sanpham where sanpham.MALSP = ? and TINHTRANG = 1");
                ps.setString(1, t.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    res += rs.getInt(1);
                }
                rs.close();
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Product> getProductPage(int pageid, int total, String idType) {
        List<Product> result = new LinkedList<>();
        try {
            PreparedStatement ps = null;
            if (idType == null) {
                ps = DBConnect.getInstance().get("select MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, MALSP from sanpham where TINHTRANG = 1 limit ?, ?");
                ps.setInt(1, pageid - 1);
                ps.setInt(2, total);
            } else {
                ps = DBConnect.getInstance().get("select MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, MALSP from sanpham where sanpham.MALSP = ? and TINHTRANG = 1 limit ?, ?");
                ps.setString(1, idType);
                ps.setInt(2, pageid - 1);
                ps.setInt(3, total);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7));
                p.setPics(PictureDao.getInstance().getByIdProduct(p.getId()));
                p.setWeights(WeightDao.getInstance().getByIdProduct(p.getId()));
                result.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Product> getByParentType(String id, int pageid, int total) {
        List<Product> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, sanpham.MALSP from sanpham join loaisp on sanpham.MALSP = loaisp.MALSP where loaisp.PHANLOAICHA = ? and TINHTRANG = 1 limit ?, ?");
            ps.setString(1, id);
            ps.setInt(2, pageid - 1);
            ps.setInt(3, total);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7));
                p.setPics(PictureDao.getInstance().getByIdProduct(p.getId()));
                p.setWeights(WeightDao.getInstance().getByIdProduct(p.getId()));
                res.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Product> searchByName(String txtSearch, int pageid, int total) {
        List<Product> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, MALSP FROM sanpham WHERE TENSP like ? and TINHTRANG = 1 limit ?,?");
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, pageid - 1);
            ps.setInt(3, total);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7));
                p.setPics(PictureDao.getInstance().getByIdProduct(p.getId()));
                p.setWeights(WeightDao.getInstance().getByIdProduct(p.getId()));
                res.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTotalBySearch(String txtSearch) {
        int result = 0;
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT count(*) FROM sanpham WHERE TENSP like ? and TINHTRANG = 1");
            ps.setString(1, "%" + txtSearch + "%");
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

    public Product getProductById(String id) {

        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, MALSP FROM sanpham WHERE MASP = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7));
                p.setPics(PictureDao.getInstance().getByIdProduct(p.getId()));
                p.setWeights(WeightDao.getInstance().getByIdProduct(p.getId()));
                rs.close();
                ps.close();
                return p;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int totalProductSoldOut() {
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT count(*) FROM sanpham join khoiluong on sanpham.MASP = khoiluong.MASP WHERE SL = 0 and TINHTRANG = 1");
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

    public List<Product> getProductEqualsType(String idType, String idP) {
        List<Product> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, MALSP from sanpham where MALSP = ? and MASP not like ? and TINHTRANG = 1");
            ps.setString(1, idType);
            ps.setString(2, idP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7));
                p.setPics(PictureDao.getInstance().getByIdProduct(p.getId()));
                p.setWeights(WeightDao.getInstance().getByIdProduct(p.getId()));
                res.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Product> getLast() {
        List<Product> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, MALSP from sanpham where TINHTRANG = 1 and DATEDIFF(Date(NOW()), NGAYTHEM) < 7 LIMIT 8");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7));
                p.setPics(PictureDao.getInstance().getByIdProduct(p.getId()));
                p.setWeights(WeightDao.getInstance().getByIdProduct(p.getId()));
                res.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Product> getDiscount() {
        List<Product> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MASP, TENSP, DISCOUNT, MOTA, NGAYTHEM, TINHTRANG, MALSP from sanpham WHERE DISCOUNT !=0 and TINHTRANG = 1 ORDER BY DISCOUNT DESC LIMIT 8");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7));
                p.setPics(PictureDao.getInstance().getByIdProduct(p.getId()));
                p.setWeights(WeightDao.getInstance().getByIdProduct(p.getId()));
                res.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Product> getHot() {
        List<Product> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select MASP from cthd GROUP BY MASP HAVING sum(SL) > 5 ORDER BY SL DESC LIMIT 8");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(getProductById(rs.getString(1)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String selectName(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT TENSP FROM sanpham WHERE MASP = ?");
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
    public String selectWeightName(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT TENSP FROM sanpham JOIN khoiluong ON sanpham.MASP = khoiluong.MASP WHERE MAKL = ?");
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
    public String selectImageName(int id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT TENSP FROM sanpham JOIN anh ON sanpham.MASP = anh.MASP WHERE MAANH = ?");
            ps.setInt(1, id);
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
    public String selectTypeName(String id) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT TENLSP from loaisp WHERE MALSP = ?");
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
    public List<Product> selectProductNameInBill(String id) {
        List<Product> res = new LinkedList<>();
        try {
            PreparedStatement ps = DBConnect.getInstance().get("SELECT sanpham.TENSP FROM (hoadon JOIN cthd ON hoadon.MAHD = cthd.MAHD) JOIN sanpham ON cthd.MASP = sanpham.MASP WHERE hoadon.MAHD = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5), rs.getBoolean(6), rs.getString(7)));

            }
            rs.close();
            ps.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }


}

