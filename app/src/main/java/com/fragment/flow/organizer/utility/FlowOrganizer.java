package com.fragment.flow.organizer.utility;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Techno Blogger on 17/5/16.
 */
public class FlowOrganizer {

    private String _last_fragment_name = "";
    private int _id_parent_frame_view;
    private FragmentManager _fragmnet_manager;
    private FragmentActivity _activity;

    public FlowOrganizer(FragmentActivity _activity, int idParentFameView) {
        this._activity = _activity;
        this._id_parent_frame_view = idParentFameView;
        this._fragmnet_manager = _activity.getSupportFragmentManager();
    }

    public void replace(Fragment toFragment) {
        replace(toFragment, null, false);
    }

    public void updateFragment(Bundle bundle, Fragment toFragmnt) {
        _fragmnet_manager.putFragment(bundle, _last_fragment_name, toFragmnt);

    }

    public void clearBackStack() {
        List<Fragment> framentList = _fragmnet_manager.getFragments();
        if (framentList != null) {
            for (Fragment f : framentList) {
                if (f != null)
                    Log.e("", "f name:" + f.getClass().getName());
            }
        }
        if (_fragmnet_manager.getBackStackEntryCount() > 0) {
            _fragmnet_manager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            _fragmnet_manager.beginTransaction().commit();
            if (_list_instant_state != null)
                _list_instant_state.clear();
            if (_list_back_state != null)
                _list_back_state.clear();
            Log.e("",
                    "Count back entitty"
                            + _fragmnet_manager.getBackStackEntryCount());
        }

    }

    public void popUpFragment(int popBackTo) {
        try {
            if (popBackTo > _fragmnet_manager.getBackStackEntryCount()) {
                android.util.Log.e("exception",
                        "popBackTo count is grater then back stack count");
                return;
            }
            int count = _fragmnet_manager.getBackStackEntryCount() - popBackTo;
            int id = _fragmnet_manager.getBackStackEntryAt(count).getId();
            String name = _fragmnet_manager.getBackStackEntryAt(count)
                    .getName();
            android.util.Log.e(count + " the fragment is " + id, "" + name);
            android.util.Log.e("", "yes poped " + count);
            _fragmnet_manager.popBackStack(count,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popUpFragment(String fragment_tag) {
        try {
            if (_fragmnet_manager.getBackStackEntryCount() > 0) {
                // for (int i = 0; i <
                // _fragmnet_manager.getBackStackEntryCount(); i++) {
                for (int i = _fragmnet_manager.getBackStackEntryCount(); i >= 0; i--) {
                    Log.e("the fragment is", ""
                            + _fragmnet_manager.getFragments().get(i)
                            .getClass().getSimpleName());
                    try {
                        if (_list_back_state != null
                                && _list_back_state.size() > 0) {
                            _list_back_state
                                    .remove(_list_back_state.size() - 1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (_fragmnet_manager.getFragments().get(i).getClass()
                            .getSimpleName().equalsIgnoreCase(fragment_tag)) {
                        _fragmnet_manager.popBackStack();
                        return;
                    } else
                        _fragmnet_manager.popBackStack();
                }
                // _fragmnet_manager.popBackStack(fragment_tag,
                // FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        } catch (Exception e) {
        }
    }

    public void replace(Fragment toFragment, boolean isAllowBack) {
        replace(toFragment, null, isAllowBack);
    }

    public void replace(Fragment toFragment, Bundle bundle, boolean isAllowBack) {
        hideKeyboard();
        if (toFragment == null)
            return;
        if (!isToAdd(toFragment))
            return;

        if (isPaused) {
            _list_instant_state.add(new InstanseState(toFragment, bundle,
                    isAllowBack, FragmentType.REPLACE));
            return;
        }
        if (bundle != null) {
            toFragment.setArguments(bundle);
        }

        FragmentTransaction _fragment_transiction = _fragmnet_manager
                .beginTransaction();
     /*   if (isAllowBack) {
            _fragment_transiction.addToBackStack(_last_fragment_name);
            _list_back_state.add(new InstanseState(toFragment, bundle,
                    isAllowBack, FragmentType.ADD));
        } else {
            _fragment_transiction.replace(_id_parent_frame_view, toFragment,
                    toFragment.getClass().getName()).commit();
            _last_fragment_name = toFragment.getClass().getName();
            clearBackStack();
        }*/

        if (isAllowBack) {
            _fragment_transiction.addToBackStack(_last_fragment_name);
        }

        _fragment_transiction.replace(_id_parent_frame_view, toFragment,
                toFragment.getClass().getName()).commit();

        _last_fragment_name = toFragment.getClass().getName();
        //clearBackStack();
    }

    public void add(Fragment toFragment) {
        add(toFragment, null, false);
    }

    public void add(Fragment toFragment, boolean isAllowBack) {
        add(toFragment, null, isAllowBack);
    }

    public void add(Fragment toFragment, Bundle bundle, boolean isAllowBack) {
        hideKeyboard();
        if (toFragment == null)
            return;
//        if(isToAdd(toFragment))
//            return;
        if (toFragment.getClass().getSimpleName()
                .equalsIgnoreCase("FragmentUserHomeMyOrder")) {
            if (!isToAdd(toFragment)) {
                try {
                    Log.e("Current Fragment", ""
                            + toFragment.getClass().getSimpleName());
                    if (toFragment.getClass().getSimpleName()
                            .equalsIgnoreCase("FragmentUserHomeMyOrder")) {
                        popUpFragment(toFragment.getClass().getSimpleName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        if (isPaused) {
            _list_instant_state.add(new InstanseState(toFragment, bundle,
                    isAllowBack, FragmentType.ADD));
            return;
        }

        if (bundle != null) {
            toFragment.setArguments(bundle);
        }

        FragmentTransaction _fragment_transiction = _fragmnet_manager
                .beginTransaction();
        if (isAllowBack) {
            _fragment_transiction.addToBackStack(_last_fragment_name);
            _list_back_state.add(new InstanseState(toFragment, bundle,
                    isAllowBack, FragmentType.ADD));
        }
        //  else {
        _fragment_transiction.add(_id_parent_frame_view, toFragment,
                toFragment.getClass().getName()).commit();
        _last_fragment_name = toFragment.getClass().getName();
        // clearBackStack();
        //   }
    }

    private boolean isToAdd(Fragment toFragment) {
        if (_list_back_state == null)
            return true;
        for (int i = 0; i < _list_back_state.size(); i++) {
            InstanseState instanseState = _list_back_state
                    .get(i);
            Fragment _fragment = instanseState.fragment;
            if (_fragment == null)
                break;
            String name1 = toFragment.getClass().getName();
            String name2 = _fragment.getClass().getName();
            if (name1.equalsIgnoreCase(name2))
                return false;
        }
        return true;
    }

    public boolean isToAdd(FragmentManager fragmentManager, Fragment fragment) {
        if (fragment == null)
            return false;
        List<Fragment> _list_fragment = fragmentManager.getFragments();
        if (_list_fragment == null)
            return true;
        for (Fragment _fragment : _list_fragment) {
            if (_fragment == null)
                break;
            if (fragment.getClass().getName()
                    .equalsIgnoreCase(_fragment.getClass().getName()))
                return false;

        }
        return true;
    }

    public boolean hasNoMoreBack() {
        return _fragmnet_manager.getBackStackEntryCount() == 0;
    }

    public String getCurrentFragmentTag() {
        return _last_fragment_name;
    }

    public void onBackPress(String back) {
        try {
            if (_list_back_state != null && _list_back_state.size() > 0) {
                _list_back_state.remove(_list_back_state.size() - 1);
                if (_list_back_state != null && _list_back_state.size() > 0) {
                    InstanseState instanseState = _list_back_state
                            .get(_list_back_state.size() - 1);
                    setCurrentFragmentTag(instanseState.fragment.getClass()
                            .getName());
                } else if (back != null)
                    _last_fragment_name = back;
            } else {
                if (back != null)
                    _last_fragment_name = back;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrentFragmentTag(String tag) {
        _last_fragment_name = tag;
    }

    private boolean isPaused = false;

    public void onPause() {
        isPaused = true;
    }

    public void onResume() {
        isPaused = false;
        if (_list_instant_state != null) {
            Log.e("", "size to exc:" + _list_instant_state.size());
            for (int i = 0; i < _list_instant_state.size(); i++) {
                InstanseState instanseState = _list_instant_state.get(i);
                switch (instanseState._fragment_type) {
                    case ADD:
                        add(instanseState.fragment, instanseState.bundle,
                                instanseState.isToBack);
                        break;
                    case REPLACE:
                        replace(instanseState.fragment, instanseState.bundle,
                                instanseState.isToBack);
                        break;
                    default:
                        break;
                }
            }
            _list_instant_state.clear();
        }
    }

    public enum FragmentType {
        ADD, REPLACE
    }

    private void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) _activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(_activity.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

    public class InstanseState {
        public Fragment fragment;
        public boolean isToBack;
        public Bundle bundle;
        public FragmentType _fragment_type;

        public InstanseState(Fragment fragment, Bundle bundle,
                             boolean isToBack, FragmentType _fragment_type) {
            this.fragment = fragment;
            this.isToBack = isToBack;
            this.bundle = bundle;
            this._fragment_type = _fragment_type;
        }
    }

    private List<InstanseState> _list_instant_state = new ArrayList<InstanseState>();
    private List<InstanseState> _list_back_state = new ArrayList<InstanseState>();

}