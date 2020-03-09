package com.example.yexin.menu6.Sideslip.Sideslip_center.Setting;

/**
 * Created by DELL on 2019/10/17.
 */

public class Account {
    private int icon_head;
    private String phone;
    private int icon_verify;

    public Account(){}
    public Account(int icon_head,String phone,int icon_verify){
        this.icon_head = icon_head;
        this.phone = phone;
        this.icon_verify = icon_verify;
    }

    public int getIcon_head() {
        return icon_head;
    }

    public void setIcon_head(int icon_head) {
        this.icon_head = icon_head;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIcon_verify() {
        return icon_verify;
    }

    public void setIcon_verify(int icon_verify) {
        this.icon_verify = icon_verify;
    }
}
