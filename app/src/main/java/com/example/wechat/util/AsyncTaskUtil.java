package com.example.wechat.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wechat.Beans.ChatBeans;
import com.example.wechat.Beans.DataBeans;
import com.example.wechat.Beans.LoginBeans;
import com.example.wechat.Beans.UserBeans;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

public class AsyncTaskUtil {
    private String name,password,chat;
    public static String user;

    public AsyncTaskUtil(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
    }

    public AsyncTaskUtil(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public AsyncTaskUtil(String chat) {
        this.chat = chat;
    }

    public AsyncTaskUtil() {
    }

    public void  AsyncTaskBeans(final Context context, final DataCallback dataCallback, final String md5user){

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, DataBeans> voidVoidDataBeansAsyncTask = new AsyncTask<Void, Void, DataBeans>() {
            Exception exception;
            @Override
            protected DataBeans doInBackground(Void... voids) {
                try {
                    return ListDataBeans(context,md5user);
                } catch (IOException e) {
                    e.printStackTrace();
                    this.exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(DataBeans dataBeans) {
                if (dataBeans == null || exception != null){
                    dataCallback.onFailed(exception);
                }else{
                    dataCallback.onSuccess(dataBeans);
                }
            }

        };
        voidVoidDataBeansAsyncTask.execute();
    }

    private DataBeans ListDataBeans(Context context,String md5user) throws IOException {
        DataBeans dataBeans = new DataBeans();
        if (name!=null&&user!=null&&password!=null){
            dataBeans = new Gson().fromJson(HttpUtil.registerData(context, name, user, password), DataBeans.class);
            return dataBeans;
        }else if (user!=null&&password!=null){
            LoginBeans loginBeans = new Gson().fromJson(HttpUtil.loginData(context, user, password), LoginBeans.class);
            dataBeans.setLoginBeans(loginBeans);
            return dataBeans;
        }else if (chat!=null){
            List<ChatBeans> chatBeansList = new Gson().fromJson(HttpUtil.chatData(context, md5user, chat), new TypeToken<List<ChatBeans>>() {}.getType());
            dataBeans.setChatBeans(chatBeansList);
            return dataBeans;
        }else {
            List<UserBeans> userBeansList = new Gson().fromJson(HttpUtil.userData(context),new TypeToken<List<UserBeans>>(){}.getType());
            dataBeans.setUserBeans(userBeansList);
            return dataBeans;
        }
    }

    public interface DataCallback {
        void onSuccess(DataBeans dataBeans);
        void onFailed(Exception ex);
    }
}
