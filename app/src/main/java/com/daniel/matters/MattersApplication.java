package com.daniel.matters;

import android.app.Application;
import com.raizlabs.android.dbflow.config.FlowManager;

/*
 * Created by dabraham on 1/10/16.
 */
public class MattersApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

}
