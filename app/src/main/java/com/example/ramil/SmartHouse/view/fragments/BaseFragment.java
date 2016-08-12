package com.example.ramil.SmartHouse.view.fragments;

import android.support.v4.app.Fragment;

import com.example.ramil.SmartHouse.presenter.BasePresenter;

/**
 * Created by Ramil on 26.07.2016.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}
