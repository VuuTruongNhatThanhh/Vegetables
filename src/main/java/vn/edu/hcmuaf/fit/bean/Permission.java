package vn.edu.hcmuaf.fit.bean;

import java.io.Serializable;
import  java.util.List;

public class Permission implements Serializable {
    private int id;
    private List<Resource> resource;
    private String u_id;
    private  int per;

    public Permission() {
    }

    public Permission(int id, List<Resource> resource, String u_id, int per) {
        this.id = id;
        this.resource = resource;
        this.u_id = u_id;
        this.per = per;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Resource> getResource() {
        return resource;
    }

    public void setResource(List<Resource> resource) {
        this.resource = resource;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

}
