package com.example.slidedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zqh on 2017/8/16.
 */

public class AppStore extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getBaseContext();

    }

    public static Context getApplication(){
        return mContext;
    }
}
