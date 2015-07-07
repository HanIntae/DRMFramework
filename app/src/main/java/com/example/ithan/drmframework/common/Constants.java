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

    public final static String MIME_TYPE_WMV = "audio/x-ms-wmv";

    public final static String[] CONTENT_PATH = {
        Environment.getExternalStorageDirectory().getPath() + "/Movies/Bear_Video_OPLs0.pyv",
        Environment.getExternalStorageDirectory().getPath() + "/Movies/Bear_Video_OPLs100.pyv",
        Environment.getExternalStorageDirectory().getPath() + "/Movies/Jazz_Audio_OPLs100.pyv",
        Environment.getExternalStorageDirectory().getPath() + "/Movies/Lake_UncompDigVideo_OPL250.pyv"
    };

    public final static String CONTENT_PATH_1 = Environment.getExternalStorageDirectory().getPath() + "/Movies/Bear_Video_OPLs0.pyv";
    public final static String CONTENT_PATH_2 = Environment.getExternalStorageDirectory().getPath() + "/Movies/Bear_Video_OPLs100.pyv";
    public final static String CONTENT_PATH_3 = Environment.getExternalStorageDirectory().getPath() + "/Movies/Jazz_Audio_OPLs100.pyv";
    public final static String CONTENT_PATH_4 = Environment.getExternalStorageDirectory().getPath() + "/Movies/Lake_UncompDigVideo_OPL250.pyv";
}
