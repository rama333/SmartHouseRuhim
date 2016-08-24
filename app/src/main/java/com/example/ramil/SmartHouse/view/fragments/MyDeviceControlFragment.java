package com.example.ramil.SmartHouse.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.MyDeviceControlPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Dimmer;

/**
 * Created by Ramil on 11.08.2016.
 */
public class MyDeviceControlFragment extends BaseFragment implements MyDeviceControlView {

    private static final String BUNDLE_DEVICE_CONTROL = "BUNDLE_DEVICE_CONTROL";
    public static final String DIMMER = "dimmer";
    private float level = 10;
    private float i,b = 0;
    private float y;
    private View v;
    int deviceId;
    Dimmer dimmer;
    TextView textView;



    MyDeviceControlPresenter myDeviceControlPresenter = new MyDeviceControlPresenter(this);

    public static MyDeviceControlFragment newInstance(int id) {
        MyDeviceControlFragment myDeviceControlFragment = new MyDeviceControlFragment();

        Bundle args = new Bundle();
        args.putInt(BUNDLE_DEVICE_CONTROL, id);
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

        Bundle bundle = getArguments();
        deviceId = bundle.getInt(BUNDLE_DEVICE_CONTROL);

        dimmer = new Dimmer();
        dimmer.getData().setType(DIMMER);
        v = view;
        textView = (TextView) view.findViewById(R.id.level);
        textView.setText(String.valueOf((int)level));

        view.setOnTouchListener((view1, event) -> { action(event, view1);
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
