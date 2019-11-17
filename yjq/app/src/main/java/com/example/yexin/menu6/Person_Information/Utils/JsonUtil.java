package com.example.yexin.menu6.Person_Information.Utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by 23646 on 2019/10/22.
 */

public class JsonUtil {
    private static Gson gson = new Gson();

    @SuppressWarnings("hiding")
    public static <T> T parseJson(String response, Class<T> clazz) {
        try {
            return gson.fromJson(response, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseJson(String response, Type type) {
        try {
            return gson.fromJson(response, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
