package com.example.ithan.drmframework.util;

import android.util.Log;

import com.example.ithan.drmframework.common.Constants;

/**
 * Created by ithan on 2015. 7. 8..
 */
public class LogInfo {
    private static final String TAG = "DRMFramework";

    public static void d(String log) {
        if (Constants.debug) {
            Log.d(TAG, log);
        }
    }
}
