package com.example.ramil.SmartHouse.view.fragments.devices;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.activity.ActivityCallback;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.devices.RosseteDeviceListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.adapters.devices.DeviceRossetAdapter;
import com.example.ramil.SmartHouse.view.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ramil on 30.07.2016.
 */
public class RosseteTab extends BaseFragment implements RosseteTabView {

    public static final String BUNDLE_ROSSETE = "BUNDLE_ROSSETE";
    @Bind(R.id.rv)
    RecyclerView recyclerViewU;

    @Bind(R.id.On)
    TextView on;

    @Bind(R.id.Off)
    TextView off;

    @Bind(R.id.swipeDevice)
    SwipeRefreshLayout swipeRefreshLayout;

    private int id;

    private View view;

    public void setId(int id) {
        this.id = id;
    }

    private DeviceRossetAdapter deviceAdapter;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private RosseteDeviceListPresenter myDeviceRosseteListPresenter = new RosseteDeviceListPresenter(this);

    private ActivityCallback activityCallback;


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

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.my_device_tab_rosete, container, false);

        ButterKnife.bind(this, view);

        if (savedInstanceState != null)
            id = savedInstanceState.getInt(BUNDLE_ROSSETE);

        deviceAdapter = new DeviceRossetAdapter(new ArrayList<>(), getActivity(), myDeviceRosseteListPresenter, this);

        myDeviceRosseteListPresenter.ShowDevices(id);

        swipeRefreshLayout.setOnRefreshListener(() -> myDeviceRosseteListPresenter.ShowDevices(id));

        Log.d("TAG", " onCreate test" + id);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL);
        //TODO helpers -> recyclerViewU.addItemDecoration(new SpacesItemDecoration(R.dimen.height));
        recyclerViewU.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewU.setHasFixedSize(true);
        recyclerViewU.setAdapter(deviceAdapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt(BUNDLE_ROSSETE, id);
    }

    @Override
    public void showList(List<Device> list) {
        deviceAdapter.setDeviceList(list);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void startFragmentRossete(int id, String name) {
        activityCallback.startFragmentDeviceRosseteControl(id, name);
    }

    @Override
    public void ShowError(String error) {
        Snackbar.make(recyclerViewU, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return myDeviceRosseteListPresenter;
    }
}
