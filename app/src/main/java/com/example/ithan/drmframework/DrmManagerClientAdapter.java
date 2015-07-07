package com.example.ithan.drmframework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ithan on 2015. 6. 29..
 */
public class DrmManagerClientAdapter extends BaseAdapter {
    public enum DRM_MANAGER_CLIENT_API {
        SELECT_CONTENTS,
        GET_AVAILABLE_DRM_ENGINES,
        CAN_HANDLE,
        CHECK_RIGHTS_STATUS,
        CHECK_RIGHTS_STATUS_PLAY,
        GET_DRM_OBJECT_TYPE,
        GET_ORIGINAL_MIME_TYPE,
        ACQUIRE_RIGHTS
        ;
    }

    private Context mContext = null;

    public DrmManagerClientAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return DRM_MANAGER_CLIENT_API.values().length;
    }

    @Override
    public DRM_MANAGER_CLIENT_API getItem(int position) {
        DRM_MANAGER_CLIENT_API[] drmManagerClientApis = DRM_MANAGER_CLIENT_API.values();

        return drmManagerClientApis[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String getItemString(int position) {
        return getItem(position).toString();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_drm_manager_client, null);
        }

        ((TextView) convertView.findViewById(R.id.tv_item_drm_manager_client)).setText(getItemString(position));

        return convertView;
    }
}
