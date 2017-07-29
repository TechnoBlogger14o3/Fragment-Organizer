package com.fragment.flow.organizer.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fragment.flow.organizer.R;
import com.fragment.flow.organizer.databinding.FragmentFiveBinding;
import com.fragment.flow.organizer.databinding.FragmentOneBinding;

/**
 * Created by Techno Blogger on 29/7/17.
 */

public class FragmentFive extends Fragment {

    private FragmentFiveBinding fragmentFiveBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentFiveBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_five, container, false);
        View rootView = fragmentFiveBinding.getRoot();
        return rootView;
    }
}
