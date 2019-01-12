package com.example.wechat.util;

import android.content.Context;
import android.content.SharedPreferences;

public class DataUtil {
    public void LoginStorage(Context context,String user,String password) {
        SharedPreferences userSetting = context.getSharedPreferences("loginData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =userSetting.edit();
        editor.putString("user",user);
        editor.putString("password",password);
        editor.apply();
    }
}
