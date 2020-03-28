package com.example.yexin.menu6.Index.fragmenttwo_fourballs;

/**
 * Created by yexin on 20-3-15.
 */

public class fragmenttwo_info {
    private String tv_title;
    private String gamedetailsid;


    private String im_src;
    private String tv_detailinfo;
    private String tv_time;




    public fragmenttwo_info(String tv_title, String tv_time, String tv_detailinfo, String im_src,String gamedetailsid) {
        this.tv_detailinfo = tv_detailinfo;
        this.tv_time = tv_time;
        this.tv_title=tv_title;
        this.im_src=im_src;
        this.gamedetailsid=gamedetailsid;
    }

    public String getTv_detailinfo() {
        return tv_detailinfo;
    }

    public String getTv_time() {
        return tv_time;
    }

    public void setTv_detailinfo(String tv_detailinfo) {
        this.tv_detailinfo = tv_detailinfo;
    }

    public void setTv_time(String tv_time) {
        this.tv_time = tv_time;
    }

    public String getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title = tv_title;
    }

    public String getIm_src() {
        return im_src;
    }

    public void setIm_src(String im_src) {
        this.im_src = im_src;
    }

    public String getGamedetailsid() {
        return gamedetailsid;
    }

    public void setGamedetailsid(String gamedetailsid) {
        this.gamedetailsid = gamedetailsid;
    }

}
