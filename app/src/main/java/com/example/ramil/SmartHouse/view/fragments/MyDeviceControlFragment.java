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

    float x,y;
    float level = 10;
    float i=0;
    float b=0;
    View v;
    MyDeviceControlPresenter myDeviceControlPresenter = new MyDeviceControlPresenter(this);

    public static MyDeviceControlFragment newInstance() {
        MyDeviceControlFragment myDeviceControlFragment = new MyDeviceControlFragment();

        return myDeviceControlFragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
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

        Dimmer dimmer = new Dimmer();

        dimmer.getData().setType("dimmer");

        v = view;

        TextView textView = (TextView) view.findViewById(R.id.level);
        textView.setText("10");

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                x = event.getX();
                y = event.getY();

                y = view.getHeight() - event.getY();


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
                        myDeviceControlPresenter.setLevel(1,dimmer);
                    default:
                }

                return true;
            }
        });


        return view;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void ShowError(String error) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show();

    }
}
