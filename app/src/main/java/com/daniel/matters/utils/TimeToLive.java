package com.daniel.matters.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.daniel.matters.MattersApplication;
import com.daniel.matters.R;

/**
 * Created by dabraham on 1/17/16.
 */
public class TimeToLive {

    private static final long SECONDS_PER_MINUTE = 60000;

    /**
     * check if it is time to update matters
     * @return whether or not it is time to update the matters list
     */
    public static boolean updateMatters() {
        long currentTime = System.currentTimeMillis();

        long lastMattersUpdateTime = getLastMattersUpdateTimeInMillis();
        long mattersTtl = getMattersTtl() * SECONDS_PER_MINUTE;

        return currentTime - lastMattersUpdateTime > mattersTtl;

    }

    private static long getLastMattersUpdateTimeInMillis() {
        Context context = MattersApplication.getContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String lastUpdateTimeKey = context.getString(R.string.key_matters_last_update_time);
        return sharedPref.getLong(lastUpdateTimeKey, 0);
    }

    private static int getMattersTtl() {
        Context context = MattersApplication.getContext();
        return context.getResources().getInteger(R.integer.matters_ttl);
    }

    public static void setLastMattersUpdateTime() {
        long currentTime = System.currentTimeMillis();
        Context context = MattersApplication.getContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String lastUpdateTimeKey = context.getString(R.string.key_matters_last_update_time);
        sharedPref.edit()
                .putLong(lastUpdateTimeKey, currentTime)
                .apply();
    }

}
