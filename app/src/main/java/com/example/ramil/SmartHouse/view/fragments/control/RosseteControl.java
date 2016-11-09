package com.example.ramil.SmartHouse.view.fragments.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.activity.ActivityCallback;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.view.fragments.BaseFragment;

/**
 * Created by Ramil on 24.09.2016.
 */

public class RosseteControl extends BaseFragment implements RosseteControlView {

    private static final String BUNDLE_ROSSETE_CONTROL = "BUNDLE_ROSSETE_CONTROL";
    private static final String BUNDLE_ROSSETE_CONTROL_NAME_ACTIONBAR = "BUNDLE_ROSSETE_CONTROL_NAME_ACTIONBAR";
    public static final String ROSSETE = "rossete";
    private ActivityCallback activityCallback;
    private ActionBar actionBar;
    private int deviceId;

    public static RosseteControl newInstance(int id, String name) {
        RosseteControl deviceFragmentRosseteControl = new RosseteControl();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_ROSSETE_CONTROL, id);
        args.putString(BUNDLE_ROSSETE_CONTROL, name);
        deviceFragmentRosseteControl.setArguments(args);

        return deviceFragmentRosseteControl;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_rossete,container, false);

        toolbar();

        Bundle bundle = getArguments();
        deviceId = bundle.getInt(BUNDLE_ROSSETE_CONTROL);
        String name = bundle.getString(BUNDLE_ROSSETE_CONTROL);

        actionBar.setTitle(name);

        return view;
    }


    private void toolbar() {
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void startRosseteFragment(int id, String name) {
        activityCallback.startFragmentDeviceRosseteControl(id, name);
    }

    @Override
    public void ShowError(String error) {

    }
}
