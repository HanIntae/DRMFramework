package com.example.ithan.drmframework.util;

import android.content.Context;
import android.drm.DrmInfoRequest;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.os.Build;

/**
 * Created by ithan on 2015. 6. 30..
 */
public class DrmManager {

    private Context mContext = null;
    private DrmManagerClient mDrmManagerClient = null;

    public DrmManager(Context context) {
        mContext = context;

        mDrmManagerClient = new DrmManagerClient(mContext);
    }

    public void release() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            mDrmManagerClient.release();
        }

        mDrmManagerClient = null;
    }

    public String[] getAvailableDrmEngines() {
        return mDrmManagerClient.getAvailableDrmEngines();
    }

    public boolean canHandle(String path, String mimeType) {
        return mDrmManagerClient.canHandle(path, mimeType);
    }

    public boolean canHandle(Uri uri, String mimeType) {
        return mDrmManagerClient.canHandle(uri, mimeType);
    }

    public int checkRightsStatus(String path) {
        return mDrmManagerClient.checkRightsStatus(path);
    }

    public int checkRightsStatus(String path, int action) {
        return mDrmManagerClient.checkRightsStatus(path, action);
    }

    public int checkRightsStatus(Uri uri) {
        return mDrmManagerClient.checkRightsStatus(uri);
    }

    public int checkRightsStatus(Uri uri, int action) {
        return mDrmManagerClient.checkRightsStatus(uri, action);
    }

    public int getDrmObjectType(String path, String mimeType) {
        return mDrmManagerClient.getDrmObjectType(path, mimeType);
    }

    public int getDrmObjectType(Uri uri, String mimeType) {
        return mDrmManagerClient.getDrmObjectType(uri, mimeType);
    }

    public String getOriginalMimeType(String path) {
        return mDrmManagerClient.getOriginalMimeType(path);
    }

    public String getOriginalMimeType(Uri uri) {
        return mDrmManagerClient.getOriginalMimeType(uri);
    }

    public int acquireRights(DrmInfoRequest drmInfoRequest) {
        return mDrmManagerClient.acquireRights(drmInfoRequest);
    }
}
