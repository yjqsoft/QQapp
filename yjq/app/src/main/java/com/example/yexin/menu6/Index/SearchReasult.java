package com.example.yexin.menu6.Index;

/**
 * Created by 23646 on 2019/11/16.
 */

public class SearchReasult {
    private String ballclub_name;
    private String appraise;
    private String address;
    private String distance;
    private String price;

    public SearchReasult(String ballclub_name, String appraise, String address, String distance, String price) {
        this.ballclub_name = ballclub_name;
        this.appraise = appraise;
        this.address = address;
        this.distance = distance;
        this.price = price;
    }

    public String getBallclub_name() {
        return ballclub_name;
    }

    public void setBallclub_name(String ballclub_name) {
        this.ballclub_name = ballclub_name;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
