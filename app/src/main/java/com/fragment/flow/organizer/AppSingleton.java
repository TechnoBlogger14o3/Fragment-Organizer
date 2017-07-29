package com.fragment.flow.organizer;

import android.app.Application;
import android.content.Context;

import com.fragment.flow.organizer.activities.MainActivity;
import com.fragment.flow.organizer.utility.FlowOrganizer;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Techno Blogger on 29/7/17.
 */

public class AppSingleton extends Application {

    private static AppSingleton _promoter;
    private MainActivity _mainActivity;
    private FlowOrganizer _flowOrganizer;


    public AppSingleton() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        _promoter = this;
    }

    public static AppSingleton getInstance() {
        if (_promoter == null)
            _promoter = new AppSingleton();
        return _promoter;
    }

    public void init(MainActivity _mainActivity) {
        this._mainActivity = _mainActivity;
    }

    public FlowOrganizer getFlowOrganization() {
        if (_flowOrganizer == null)
            _flowOrganizer = new FlowOrganizer(_mainActivity, R.id.frame_parent);
        return _flowOrganizer;
    }

    public MainActivity getActivityInstance() {
        return _mainActivity;
    }

    public void onResume() {
        if (_flowOrganizer != null)
            _flowOrganizer.onResume();
    }

    public void onPause() {
        if (_flowOrganizer != null)
            _flowOrganizer.onPause();
    }

    public void onDestroy() {
        _promoter = null;
        _mainActivity = null;
        _flowOrganizer = null;
    }

}
