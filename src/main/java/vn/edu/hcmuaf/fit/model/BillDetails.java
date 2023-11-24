package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.Dao.KeyDao;
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

    public static String getidinfo(String id_bill){
        return KeyDao.getInstance().getId_infoinBilldetail(id_bill);
    }

    public static String gethash(String id_bill){
        return KeyDao.getInstance().getHash_inBilldetail(id_bill);
    }

    public static String getiduser(String id_bill){
        return KeyDao.getInstance().getIDuser_inBilldetail(id_bill);
    }

    public static String getstatus(String id_bill){
        return KeyDao.getInstance().getStatus_inBilldetail(id_bill);
    }

    public static boolean verify(String idbill) throws Exception {

        return KeyDao.verifySignature(KeyDao.getInstance().getidProductInBill(idbill) + KeyDao.getInstance().getWeightProductInBill(idbill) + KeyDao.getInstance().getAmountProductInBill(idbill) + KeyDao.getInstance().getPriceProductInBill(idbill) + KeyDao.getInstance().getDateBill(idbill)
                + KeyDao.getInstance().getTotalBill(idbill)  + KeyDao.getInstance().getIDuserBill(idbill) + KeyDao.getInstance().getIDinfoBill(idbill) + KeyDao.getInstance().getFeeBill(idbill) + KeyDao.getInstance().getNameUser(getidinfo(idbill)) + KeyDao.getInstance().getPhoneUser(getidinfo(idbill))
                + KeyDao.getInstance().getProvinceUser(getidinfo(idbill)) + KeyDao.getInstance().getDistrictUser(getidinfo(idbill)) + KeyDao.getInstance().getWardUser(getidinfo(idbill)) + KeyDao.getInstance().getAddressUser(getidinfo(idbill)), gethash(idbill),KeyDao.getInstance().getpublickey(getiduser(idbill)));


    }
}
