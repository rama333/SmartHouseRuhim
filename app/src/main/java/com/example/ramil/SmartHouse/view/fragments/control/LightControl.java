package com.example.ramil.SmartHouse.view.fragments.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.control.DeviceControlPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Dimmer;
import com.example.ramil.SmartHouse.view.fragments.BaseFragment;

/**
 * Created by Ramil on 11.08.2016.
 */
public class LightControl extends BaseFragment implements LightControlView {

    private static final String BUNDLE_DEVICE_CONTROL = "BUNDLE_DEVICE_CONTROL";
    private static final String BUNDLE_DEVICE_CONTROL_NAME_ACTIONBAR = "BUNDLE_DEVICE_CONTROL_NAME_ACTIONBAR";
    public static final String DIMMER = "dimmer";
    private float level = 10;
    private float i,b = 0;
    private float y;
    private View v;
    private int deviceId;
    private ActionBar actionBar;
    Dimmer dimmer;
    TextView textView;



    DeviceControlPresenter myDeviceControlPresenter = new DeviceControlPresenter(this);

    public static LightControl newInstance(int id, String name) {
        LightControl myDeviceControlFragment = new LightControl();

        Bundle args = new Bundle();
        args.putInt(BUNDLE_DEVICE_CONTROL, id);
        args.putString(BUNDLE_DEVICE_CONTROL_NAME_ACTIONBAR, name);
        myDeviceControlFragment.setArguments(args);

        return myDeviceControlFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            //TODO edit activity
            //activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle  savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.my_device_control, container, false);

        toolbar();

        Bundle bundle = getArguments();
        deviceId = bundle.getInt(BUNDLE_DEVICE_CONTROL);
        String name = bundle.getString(BUNDLE_DEVICE_CONTROL_NAME_ACTIONBAR);

        actionBar.setTitle(name);

        dimmer = new Dimmer();
        dimmer.getData().setType(DIMMER);
        v = view;
        textView = (TextView) view.findViewById(R.id.level);
        textView.setText(String.valueOf((int)level));

        view.setOnTouchListener((view1, event) -> {
            action(event, view1);
            return true;
        });

        return view;
    }

    private void action(MotionEvent event, View view1) {
        y = event.getY();
        y = view1.getHeight() - event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                b = y;
            case MotionEvent.ACTION_MOVE:
                i = level + (y - b);
                if (i >= 1000) {
                    i = 1000;
                    textView.setText(String.valueOf((int) (i / 10)));
                } else if (i <= 0) {
                    i = 0;
                    textView.setText(String.valueOf((0)));
                } else {
                    textView.setText(String.valueOf((int) i / 10));
                }
                break;
            case MotionEvent.ACTION_UP:
                level = i;
                float t = i / 10;
                textView.setText(String.valueOf((int) t));
                dimmer.getData().setBrightValue((int) t);
                myDeviceControlPresenter.setLevel(deviceId, dimmer);
            default:
        }
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
        return myDeviceControlPresenter;
    }

    @Override
    public void show() {

    }

    @Override
    public void ShowError(String error) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show();

    }
}
