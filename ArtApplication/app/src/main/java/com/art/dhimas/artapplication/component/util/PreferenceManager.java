package com.art.dhimas.artapplication.component.util;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

public class PreferenceManager {
    private static final String USER_LOGIN = "userLogin";
    private static final String IS_LOGIN = "isLogin";

    public PreferenceManager(Context context){
        Hawk.init(context).build();
    }

    public static void setUserLogin(String username, String password) {
        Hawk.put(USER_LOGIN, new String[]{username, password});
    }

    public static String[] getUserLogin() {
        return Hawk.get(USER_LOGIN);
    }

    public static void setIsLogin(boolean isLogin) {
        Hawk.put(IS_LOGIN, isLogin);
    }

    public static boolean getIsLogin() {
        return Hawk.get(IS_LOGIN, false);
    }

}
