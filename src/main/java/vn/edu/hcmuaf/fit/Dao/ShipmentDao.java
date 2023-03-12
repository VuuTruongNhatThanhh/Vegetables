package vn.edu.hcmuaf.fit.Dao;

public class ShipmentDao {
    public static ShipmentDao instance;

    public static ShipmentDao getInstance() {
        if (instance == null)
            instance = new ShipmentDao();
        return instance;
    }

}
