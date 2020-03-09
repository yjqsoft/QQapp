package com.example.yexin.menu6.Index;

/**
 * Created by 23646 on 2019/11/16.
 */

public class SearchReasult {

    private String no;//场馆编号

    private String ballclub_name;//场馆名

    private String address;
    private String distance;//距离
    private String price;//场馆价格
    private String functionany;//场馆负责人
    private String phone;//负责人电话

    private String picture;//场馆图片
    private String appraise;//场馆评分



    private String ball;//球类型
    private String server;//场馆服务
    private String introduce;//介绍
    private String sum;//下单量
    private String floor;//地板
    private String ligth;//灯光



    private String rest;//休息区
    private String sell;//售卖
    private String sellsport;//运动物品售卖



    private String location;//球馆坐标






    public SearchReasult(String no, String ballclub_name, String address, String distance, String price, String functionany,
                         String phone, String picture, String appraise,String ball,
                         String server, String introduce, String sum, String floor, String ligth, String rest,String sell,
                         String sellsport,String location) {
        this.no = no;
        this.ballclub_name = ballclub_name;
        this.address = address;
        this.distance = distance;
        this.price = price;
        this.functionany = functionany;
        this.phone = phone;
        this.picture = picture;
        this.appraise = appraise;
        this.ball=ball;
        this.server = server;
        this.introduce = introduce;
        this.sum = sum;
        this.floor = floor;
        this.ligth = ligth;
        this.rest=rest;
        this.sell = sell;
        this.sellsport = sellsport;
        this.location=location;
    }


    public SearchReasult(String ballclub_name, String appraise, String address, String distance, String price) {
        this.ballclub_name = ballclub_name;//场馆名
        this.appraise = appraise;//场馆评价
        this.address = address;//场馆地址
        this.distance = distance;//场馆位置
        this.price = price;//场馆价格
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getFunctionany() {
        return functionany;
    }

    public void setFunctionany(String functionany) {
        this.functionany = functionany;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getLigth() {
        return ligth;
    }

    public void setLigth(String ligth) {
        this.ligth = ligth;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getSellsport() {
        return sellsport;
    }

    public void setSellsport(String sellsport) {
        this.sellsport = sellsport;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
