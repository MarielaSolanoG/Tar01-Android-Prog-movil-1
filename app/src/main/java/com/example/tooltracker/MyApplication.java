package com.example.tooltracker;

import android.app.Application;

public class MyApplication extends Application {
    private static ProductManager productManager;

    @Override
    public void onCreate() {
        super.onCreate();
        productManager = new ProductManager();
    }

    public static ProductManager getProductManager() {
        return productManager;
    }
}
