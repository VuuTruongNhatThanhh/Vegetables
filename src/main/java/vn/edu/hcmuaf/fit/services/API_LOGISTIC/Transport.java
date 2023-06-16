package vn.edu.hcmuaf.fit.services.API_LOGISTIC;


import vn.edu.hcmuaf.fit.model.Bills;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transport {
    String id;
    public Bills order;
    String created_at;
    String leadTime;

    public Transport(String id, Bills order, String created_at, String leadTime) {
        this.id = id;
        this.order = order;
        this.created_at = created_at;
        this.leadTime = leadTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bills getOrder() {
        return order;
    }

    public void setOrder(Bills order) {
        this.order = order;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id='" + id + '\'' +
                ", order=" + order +
                ", created_at='" + created_at + '\'' +
                ", leadTime='" + leadTime + '\'' +
                '}';
    }
}
