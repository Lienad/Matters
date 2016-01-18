package com.daniel.matters;

import android.app.Application;

/**
 * Created by dabraham on 1/14/16.
 */
public class MattersApplication extends Application {

    private static MattersApplication singleton;

    public MattersApplication() {
        super();
        synchronized (MattersApplication.class) {
            if (singleton == null) {
                singleton = this;
            }
        }
    }

    public static MattersApplication getContext() {
        return singleton;
    }
}
