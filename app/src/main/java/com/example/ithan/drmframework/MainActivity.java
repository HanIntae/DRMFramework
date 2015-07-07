package com.example.ithan.drmframework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.example.ithan.drmframework.common.Constants;


public class MainActivity extends ActionBarActivity {

    private ActionBar.Tab mTabPlayTs = null;
    private ActionBar.Tab mTabDrmFramework = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newView();
    }

    private void newView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mTabPlayTs = actionBar.newTab();
        mTabPlayTs.setText(getString(R.string.drm_manager_client));
        mTabPlayTs.setTabListener(mTabListener);
        actionBar.addTab(mTabPlayTs);

        mTabDrmFramework = actionBar.newTab();
        mTabDrmFramework.setText(getString(R.string.drm_framework));
        mTabDrmFramework.setTabListener(mTabListener);
        actionBar.addTab(mTabDrmFramework);
    }

    private void replaceFragment(Constants.ACTION_BAR_TYPE actionBarType, FragmentTransaction fragmentTransaction) {
        Fragment fragment = getFragment(actionBarType);

        if (fragment == null) {
            return;
        }

        fragmentTransaction.replace(R.id.activity_main_frame_container, fragment);
    }

    private Fragment getFragment(Constants.ACTION_BAR_TYPE actionBarType) {
        Fragment fragment = null;

        if (actionBarType == null) {
            return fragment;
        }

        switch (actionBarType) {
            case DRM_MANAGER_CLIENT:
                fragment = new DrmManagerClientFragment();
                break;
            case DRM_FRAMEWORK:
                fragment = new DrmFrameworkFragment();
                break;
            default:
                break;
        }

        return fragment;
    }

    private ActionBar.TabListener mTabListener = new ActionBar.TabListener() {

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            Constants.ACTION_BAR_TYPE actionBarType = null;

            if (tab == mTabPlayTs) {
                actionBarType = Constants.ACTION_BAR_TYPE.DRM_MANAGER_CLIENT;
            } else if (tab == mTabDrmFramework) {
                actionBarType = Constants.ACTION_BAR_TYPE.DRM_FRAMEWORK;
            }

            replaceFragment(actionBarType, ft);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    };
}
