package com.example.last_22.data.entity;


import android.app.Application;


import com.lvsiqian.mvplib.manger.MvpManger;


public class JDApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        MvpManger.init(this);
    }
}
