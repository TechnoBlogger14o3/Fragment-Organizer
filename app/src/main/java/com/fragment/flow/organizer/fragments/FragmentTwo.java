package com.fragment.flow.organizer.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fragment.flow.organizer.AppSingleton;
import com.fragment.flow.organizer.R;
import com.fragment.flow.organizer.databinding.FragmentOneBinding;
import com.fragment.flow.organizer.databinding.FragmentTwoBinding;

/**
 * Created by Techno Blogger on 29/7/17.
 */

public class FragmentTwo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTwoBinding fragmentTwoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_two, container, false);
        View rootView = fragmentTwoBinding.getRoot();
        AppSingleton.getInstance().getActivityInstance().setTitle(getResources().getString(R.string.fragment_two));

        return rootView;
    }
}
