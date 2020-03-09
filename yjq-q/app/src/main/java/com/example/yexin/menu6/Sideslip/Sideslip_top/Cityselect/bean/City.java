package com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.bean;

import java.util.Objects;

/**
 * Created by ye xin on 2019/10/13.
 */

public class City {
    private int id;
    private String name;
    private String pinyin;
    private boolean isHot;

    public City() {
    }

    public City(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    public City(int id, String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
        this.id = id;
    }

    public City(int id, String name, String pinyin, boolean isHot) {
        this.name = name;
        this.pinyin = pinyin;
        this.id = id;
        this.isHot = isHot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
