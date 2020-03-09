package com.example.yexin.menu6.Order;

/**
 * Created by 23646 on 2019/11/3.
 */

public class Order {
    private String order_num;
    private String order_time;
    private String order_type;
    private String order_location;
    private String order_appointment_time;
    private String order_site_num;
    private String order_price;
    private String order_status;

    public Order(String order_num, String order_time, String order_type, String order_location, String order_appointment_time, String order_site_num, String order_price, String order_status) {
        this.order_num = order_num;
        this.order_time = order_time;
        this.order_type = order_type;
        this.order_location = order_location;
        this.order_appointment_time = order_appointment_time;
        this.order_site_num = order_site_num;
        this.order_price = order_price;
        this.order_status = order_status;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_location() {
        return order_location;
    }

    public void setOrder_location(String order_location) {
        this.order_location = order_location;
    }

    public String getOrder_appointment_time() {
        return order_appointment_time;
    }

    public void setOrder_appointment_time(String order_appointment_time) {
        this.order_appointment_time = order_appointment_time;
    }

    public String getOrder_site_num() {
        return order_site_num;
    }

    public void setOrder_site_num(String order_site_num) {
        this.order_site_num = order_site_num;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
