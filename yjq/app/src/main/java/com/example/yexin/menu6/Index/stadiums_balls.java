package com.example.yexin.menu6.Index;

/**
 * Created by yexin on 19-12-7.
 * 场馆运动项目名及价格数据获取
 */

public class stadiums_balls {

    private String ballclub_ballname;
    private String ballclub_prices;

    public stadiums_balls( String ballclub_ballname, String ballclub_prices) {

        this.ballclub_ballname = ballclub_ballname;
        this.ballclub_prices = ballclub_prices;
    }


    public String getBallclub_ballname() {
        return ballclub_ballname;
    }

    public String getBallclub_prices() {
        return ballclub_prices;
    }


    public void setBallclub_ballname(String ballclub_ballname) {
        this.ballclub_ballname = ballclub_ballname;
    }

    public void setBallclub_prices(String ballclub_prices) {
        this.ballclub_prices = ballclub_prices;
    }
}
