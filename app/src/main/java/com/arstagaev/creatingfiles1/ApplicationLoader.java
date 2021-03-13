package com.arstagaev.creatingfiles1;

import android.app.Application;
import android.content.Context;

public class ApplicationLoader extends Application {

    public static volatile Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            applicationContext = getApplicationContext();
        } catch (Throwable ignore) {

        }
        if (applicationContext == null) {
            applicationContext = getApplicationContext();
        }
    }
}
