package com.example.ramil.SmartHouse.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramil.SmartHouse.ActivityCallback;
import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.MyDeviceListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.adapters.DeviceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ramil on 30.07.2016.
 */
public class LightTab extends Fragment implements MyDeviceView {

    public static final int BRIGHT_VALUE_ON = 100;
    public static final int BRIGHT_VALUE_OFF = 0;

    @Bind(R.id.rv)
    RecyclerView recyclerViewU;

    @Bind(R.id.On)
    TextView on;

    @Bind(R.id.Off)
    TextView off;

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    private DeviceAdapter deviceAdapter;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    ActivityCallback activityCallback;

    private MyDeviceListPresenter myDeviceListPresenter = new MyDeviceListPresenter(this);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            //TODO edit activity
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_device_tab_light, container, false);

        ButterKnife.bind(this, view);

        deviceAdapter = new DeviceAdapter(new ArrayList<>(), getActivity(), myDeviceListPresenter, this);

        myDeviceListPresenter.ShowDevices(id);

        Log.d("TAG", String.valueOf(id));

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL);
        //recyclerViewU.addItemDecoration(new SpacesItemDecoration(R.dimen.height));

        recyclerViewU.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewU.setAdapter(deviceAdapter);

        on.setOnClickListener(view1 -> {
            myDeviceListPresenter.OffOnDevices(id, BRIGHT_VALUE_ON);

        });

        off.setOnClickListener(view1 -> {
            myDeviceListPresenter.OffOnDevices(id, BRIGHT_VALUE_OFF);
        });



        return view;
    }



    @Override
    public void showList(List<Device> list) {

        deviceAdapter.setDeviceList(list);
        //deviceAdapter.notifyItemChanged(1, new Device(1, "werg", "wefwe"));

        //recyclerViewU.getAdapter().notifyItemChanged(0, new Device(1, "werg", "wefwe"));

    }

    @Override
    public void test(List<Device> list) {
        //list.get(0).setType("qwert");
        //deviceAdapter.notifyItemChanged(0);
    }

    @Override
    public void startFragmentDeviceControl() {
        activityCallback.startFragmentDeviceControl();
    }

    @Override
    public void ShowError(String error) {
        Snackbar.make(recyclerViewU, error, Snackbar.LENGTH_LONG).show();

    }
}