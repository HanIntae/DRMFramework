package com.example.ithan.drmframework;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ithan.drmframework.util.DrmManager;
import com.example.ithan.drmframework.common.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrmManagerClientFragment extends Fragment {
    private enum DIALOG_TYPE {
        SELECT_CONTENT, CAN_HANDLE;
    };

    private TextView mTvLog = null;

    private ListView mListView = null;
    private DrmManagerClientAdapter mDrmManagerClientAdapter = null;

    private DrmManager mDrmManager = null;

    private String mPath = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drm_manager_client, container, false);

        newView(view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindView();

        initialize();

        showDialog(DIALOG_TYPE.SELECT_CONTENT, Constants.CONTENT_PATH);
    }

    private void newView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_fragment_drm_manager_client_list);
        mTvLog = (TextView) view.findViewById(R.id.tv_fragment_drm_manager_client_list_log);
    }

    private void bindView() {
        mDrmManagerClientAdapter = new DrmManagerClientAdapter(getActivity());

        mListView.setAdapter(mDrmManagerClientAdapter);
        mListView.setOnItemClickListener(mOnItemClickListener);
    }

    private void initialize() {
        mDrmManager = new DrmManager(getActivity());
    }

    private void showDialog(final DIALOG_TYPE dialogType, String[] items) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle(getActivity().getString(R.string.dialog_title));
        alertDialog.setNegativeButton(getActivity().getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);

        for (int i = 0; i < items.length; i++) {
            adapter.add(items[i]);
        }

        alertDialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialogType == DIALOG_TYPE.SELECT_CONTENT) {
                    mPath = adapter.getItem(which);
                } else if (dialogType == DIALOG_TYPE.CAN_HANDLE) {
                    Constants.CAN_HANDLE_TYPE[] canHandleTypes = Constants.CAN_HANDLE_TYPE.values();
                    canHandle(mPath, canHandleTypes[which]);
                }

                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DrmManager drmManager = new DrmManager(getActivity());

            DrmManagerClientAdapter.DRM_MANAGER_CLIENT_API drmManagerClientApi = mDrmManagerClientAdapter.getItem(position);

            switch (drmManagerClientApi) {
                case SELECT_CONTENTS:
                    showDialog(DIALOG_TYPE.SELECT_CONTENT, Constants.CONTENT_PATH);
                    break;
                case GET_AVAILABLE_DRM_ENGINES:
                    getAvailableDrmEngines();
                    break;
                case CAN_HANDLE:
                    Constants.CAN_HANDLE_TYPE[] canHandleTypes = Constants.CAN_HANDLE_TYPE.values();
                    String[] items = new String[canHandleTypes.length];
                    for (int i = 0; i < canHandleTypes.length; i++) {
                       items[i] = canHandleTypes[i].toString();
                    }

                    showDialog(DIALOG_TYPE.CAN_HANDLE, items);
                    break;
                case CHECK_RIGHTS_STATUS:
                    int status = drmManager.checkRightsStatus(mPath);
                    if (status == DrmStore.Action.PLAY) {
                        mTvLog.setText("play rights");
                    } else if (status == DrmStore.Action.DEFAULT){
                        mTvLog.setText("default rights");
                    } else if (status == DrmStore.Action.TRANSFER) {
                        mTvLog.setText("transfer rights");
                    } else {
                        mTvLog.setText("status is " + status);
                    }
                    break;
                case CHECK_RIGHTS_STATUS_PLAY:
                    int statu1s = drmManager.checkRightsStatus(mPath, DrmStore.Action.PLAY);
                    if (statu1s == DrmStore.RightsStatus.RIGHTS_VALID) {
                        mTvLog.setText("valid rights");
                    } else {
                        mTvLog.setText("invalid rights");
                    }
                    break;
                case GET_DRM_OBJECT_TYPE:
                    drmManager.getDrmObjectType(mPath, drmManager.getOriginalMimeType(mPath));
                    break;
                case GET_ORIGINAL_MIME_TYPE:
                    String mimeType = drmManager.getOriginalMimeType(mPath);
                    if (mimeType != null) {
                        mTvLog.setText(mimeType);
                    } else {
                        mTvLog.setText("cant handle");
                    }
                    break;
                case ACQUIRE_RIGHTS:

                    break;
                default:
                    break;
            }

            drmManager.release();
        }
    };

    private void getAvailableDrmEngines() {
        String[] engines = mDrmManager.getAvailableDrmEngines();
        String text = "";
        for (String engine : engines) {
            text += engine + "\n";
        }

        if (text != null) {
            mTvLog.setText(text);
        } else {
            mTvLog.setText("no engine");
        }
    }

    private void canHandle(String path, Constants.CAN_HANDLE_TYPE canHandleType) {
        boolean result = false;

        if (canHandleType == Constants.CAN_HANDLE_TYPE.URI) {
            result = mDrmManager.canHandle(Uri.parse(path), null);
        } else if (canHandleType == Constants.CAN_HANDLE_TYPE.PATH) {
            result = mDrmManager.canHandle(path, null);
        }

        if (result) {
            mTvLog.setText("\"" + path + "\" is can handle");
        } else {
            mTvLog.setText(path + " is can not handle");
        }
    }

    private void checkRightsStatus(String path) {
        int status = mDrmManager.checkRightsStatus(path);
        if (status == DrmStore.Action.PLAY) {
            mTvLog.setText("play rights");
        } else if (status == DrmStore.Action.DEFAULT){
            mTvLog.setText("default rights");
        } else if (status == DrmStore.Action.TRANSFER) {
            mTvLog.setText("transfer rights");
        } else {
            mTvLog.setText("status is " + status);
        }
    }
}
