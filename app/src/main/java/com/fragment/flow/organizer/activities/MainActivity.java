package com.fragment.flow.organizer.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.fragment.flow.organizer.AppSingleton;
import com.fragment.flow.organizer.R;
import com.fragment.flow.organizer.databinding.ActivityMainBinding;
import com.fragment.flow.organizer.fragments.FragmentHome;

public class MainActivity extends FragmentActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        AppSingleton.getInstance().init(this);
        AppSingleton.getInstance().getFlowOrganization().add(new FragmentHome(), false);


    }

    public void setTitle(String strTitle) {
        activityMainBinding.txtTitle.setText(strTitle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppSingleton.getInstance().onDestroy();
    }
}
