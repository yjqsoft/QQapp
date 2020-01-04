package com.example.yexin.menu6.Index;

/**
 * Created by yexin on 19-12-8.
 * 获取用户名，用户评价，用户头像数据
 */

public class userinfo_adapter {
    private String username;
    private String user_evalutes;

    public String getUsername() {
        return username;
    }

    public String getUser_evalutes() {
        return user_evalutes;
    }



    public userinfo_adapter(String username, String user_evalutes) {
        this.username = username;
        this.user_evalutes = user_evalutes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser_evalutes(String user_evalutes) {
        this.user_evalutes = user_evalutes;
    }
}
