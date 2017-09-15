package com.fragment.flow.organizer.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fragment.flow.organizer.AppSingleton;
import com.fragment.flow.organizer.R;
import com.fragment.flow.organizer.databinding.FragmentOneBinding;
import com.fragment.flow.organizer.databinding.FragmentThreeBinding;

/**
 * Created by Techno Blogger on 29/7/17.
 */

public class FragmentThree extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentThreeBinding fragmentThreeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_three, container, false);
        View rootView = fragmentThreeBinding.getRoot();
        Bundle bundle = getArguments();
        String strValue = bundle.getString("keyA");
        Toast.makeText(AppSingleton.getInstance().getActivityInstance(), "Bundle Value is " + strValue, Toast.LENGTH_SHORT).show();

        AppSingleton.getInstance().getActivityInstance().setTitle(getResources().getString(R.string.fragment_three));
        return rootView;
    }
}
