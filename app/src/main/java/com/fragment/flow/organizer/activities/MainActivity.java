package com.fragment.flow.organizer.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.fragment.flow.organizer.AppSingleton;
import com.fragment.flow.organizer.R;
import com.fragment.flow.organizer.databinding.ActivityMainBinding;
import com.fragment.flow.organizer.fragments.FragmentHome;
import com.fragment.flow.organizer.utility.BaseBackHandlerFragment;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private ActivityMainBinding activityMainBinding;
    private int _backBtnCount = 0;

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

    public void onBackPressed() {

        boolean isToWorkOnback = true;
        if (AppSingleton.getInstance().getFlowOrganization() != null && !AppSingleton.getInstance().getFlowOrganization().hasNoMoreBack()) {
            List<Fragment> list = getSupportFragmentManager().getFragments();
            if (list != null) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    Fragment fragment = list.get(i);
                    if (fragment instanceof BaseBackHandlerFragment) {
                        isToWorkOnback = ((BaseBackHandlerFragment) fragment)
                                .onBackPressed();
                        break;
                    }
                }
            }
        }
        if (!isToWorkOnback)
            return;

        if (!AppSingleton.getInstance().getFlowOrganization().hasNoMoreBack())
            super.onBackPressed();
        else {
            _backBtnCount++;
            if (_backBtnCount == 2) {
                System.exit(0);
                finish();
            } else {
                Toast.makeText(AppSingleton.getInstance().getActivityInstance(), "Press back twice to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        _backBtnCount = 0;
                    }
                }, 500);
            }
        }
    }

}
