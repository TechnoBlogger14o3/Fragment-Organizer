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
import com.fragment.flow.organizer.databinding.FragmentFourBinding;

/**
 * Created by Techno Blogger on 29/7/17.
 */

public class FragmentFour extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentFourBinding fragmentFourBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_four, container, false);
        View rootView = fragmentFourBinding.getRoot();
        fragmentFourBinding.btnNextFragment.setOnClickListener(this);

        AppSingleton.getInstance().getActivityInstance().setTitle(getResources().getString(R.string.fragment_four));
        return rootView;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNextFragment:
                AppSingleton.getInstance().getFlowOrganization().add(new FragmentFour_Next(), true);
                break;
            default:
                break;
        }
    }

}
