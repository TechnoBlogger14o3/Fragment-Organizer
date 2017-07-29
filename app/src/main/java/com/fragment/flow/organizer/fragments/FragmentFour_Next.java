package com.fragment.flow.organizer.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fragment.flow.organizer.AppSingleton;
import com.fragment.flow.organizer.R;
import com.fragment.flow.organizer.databinding.FragmentFourNextBinding;
import com.fragment.flow.organizer.utility.BaseBackHandlerFragment;

/**
 * Created by Techno Blogger on 29/7/17.
 */

public class FragmentFour_Next extends BaseBackHandlerFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FragmentFourNextBinding fragmentFourNextBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_four_next, container, false);
        View rootView = fragmentFourNextBinding.getRoot();
        AppSingleton.getInstance().getActivityInstance().setTitle(getResources().getString(R.string.fragment_four_a));
        return rootView;
    }

    @Override
    public boolean onBackPressed() {
        AppSingleton.getInstance().getActivityInstance().setTitle(getResources().getString(R.string.fragment_four));
        return super.onBackPressed();
    }
}
