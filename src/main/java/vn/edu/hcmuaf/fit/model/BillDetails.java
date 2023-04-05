package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.Dao.ShipmentDetailDao;

public class BillDetails extends BillDetailDao {
    private String idBill;
    private String idP;
    private String idW;
    private int amount;
    private double total;

    public BillDetails(String idBill, String idP, String idW, int amount, double total) {
        this.idBill = idBill;
        this.idP = idP;
        this.idW = idW;
        this.amount = amount;
        this.total = total;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getIdW() {
        return idW;
    }

    public void setIdW(String idW) {
        this.idW = idW;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNameProduct(){
        return BillDetailDao.getInstance().NameProduct(idP);
    }
    public String getNameWeight(){
        return BillDetailDao.getInstance().NameWeight(idW);
    }

}
