package vn.edu.hcmuaf.fit.model;

public class ShipmentDetails {
    private String id;
    private String name;
    private String phoneNumber;
    private String province;
    private String district;
    private String ward;
    private String address;
    private String idUser;

    public ShipmentDetails(String id, String name, String phoneNumber, String province, String district, String ward, String address, String idUser) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFullAddress() {
        return this.address + ", " + this.getWard() + ", " + this.district + ", " + province;
    }
}
