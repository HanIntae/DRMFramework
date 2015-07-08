package com.example.ithan.drmframework.common;

import android.os.Environment;

/**
 * Created by ithan on 2015. 6. 29..
 */
public class Constants {

    public enum ACTION_BAR_TYPE {
        DRM_MANAGER_CLIENT, DRM_FRAMEWORK;
    }

    public enum CAN_HANDLE_TYPE {
        URI, PATH;
    }

    public enum CHECK_RIGHTS_STATUS_ACTION_TYPE {
        DEFAULT, PLAY, RINGTONE, TRANSFER, OUTPUT, PREVIEW, EXECUTE, DISPLAY;
    }

    public final static String[] CONTENT_PATH = {
        Environment.getExternalStorageDirectory().getPath() + "/Download/playready/Lake_AnalogVideo150_CGMS_A11b.pyv",
        Environment.getExternalStorageDirectory().getPath() + "/Download/playready/Bear_Video_OPLs0.pyv",
        Environment.getExternalStorageDirectory().getPath() + "/Download/playready/Bear_Video_OPLs100.pyv",
        Environment.getExternalStorageDirectory().getPath() + "/Download/playready/ChainedTestVideo1.pyv",
        Environment.getExternalStorageDirectory().getPath() + "/Download/playready/Lake_UncompDigVideo_OPL250.pyv",
        Environment.getExternalStorageDirectory().getPath() + "/Download/playready/non_silent_lic_acq.pya",
        Environment.getExternalStorageDirectory().getPath() + "/Download/widevine/sintel_main_720p_4br_tp.wvm"
    };

    public final static boolean debug = true;
}
