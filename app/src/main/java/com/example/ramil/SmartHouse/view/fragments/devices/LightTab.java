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
import com.example.ramil.SmartHouse.presenter.devices.LightDeviceListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.adapters.devices.DeviceAdapter;
import com.example.ramil.SmartHouse.view.fragments.BaseFragment;

import org.java_websocket.WebSocket;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;

/**
 * Created by Ramil on 30.07.2016.
 */
public class LightTab extends BaseFragment implements LightTabView {

    public static final int BRIGHT_VALUE_ON = 100;
    public static final int BRIGHT_VALUE_OFF = 0;
    public static final String TAG = "TAG";
    public static final String LIGT_TAP = "LigtTap";
    private StompClient mStompClient;

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

    private DeviceAdapter deviceAdapter;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private ActivityCallback activityCallback;

    private LightDeviceListPresenter myDeviceListPresenter = new LightDeviceListPresenter(this);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_device_tab_light, container, false);

        this.view = view;
        ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            id = savedInstanceState.getInt(LIGT_TAP);
        }

        deviceAdapter = new DeviceAdapter(new ArrayList<>(), getActivity(), myDeviceListPresenter, this);

        myDeviceListPresenter.ShowDevices(id);

        swipeRefreshLayout.setOnRefreshListener(() -> myDeviceListPresenter.ShowDevices(id));

        Log.d("TAG", " onCreate test" + id);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL);
        //TODO helpers -> recyclerViewU.addItemDecoration(new SpacesItemDecoration(R.dimen.height));
        recyclerViewU.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewU.setHasFixedSize(true);
        recyclerViewU.setAdapter(deviceAdapter);

        on.setOnClickListener(view1 -> myDeviceListPresenter.OffOnDevices(id, BRIGHT_VALUE_ON));

        off.setOnClickListener(view1 -> myDeviceListPresenter.OffOnDevices(id, BRIGHT_VALUE_OFF));

            connectWebSocket();



        return view;
    }

    @Override
    public void showList(List<Device> list) {
        deviceAdapter.setDeviceList(list);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void test(List<Device> list) {
        list.get(0).setType("q");
        deviceAdapter.notifyItemChanged(0);
    }

    @Override
    public void startFragmentDeviceControl(int id, String name) {
        activityCallback.startFragmentDeviceControl(id, name);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(LIGT_TAP, id);
        //mConnection.disconnect();
    }

    @Override
    public void ShowError(String error) {
        Snackbar.make(recyclerViewU, error, Snackbar.LENGTH_LONG).show();
    }

    private void connectWebSocket() {

        final String wsuri = "ws://192.168.1.110:8080/gs-guide-websocket";


        mStompClient = Stomp.over(WebSocket.class, "ws://192.168.1.110:8080/gs-guide-websocket");
        mStompClient.connect();

        mStompClient.send("/app/hello", "My first STOMP message!").subscribe();


        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            Log.d(TAG, lifecycleEvent.getType() + "");
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;

                case ERROR:
                    Log.d(TAG, "Error", lifecycleEvent.getException());
                    break;

                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
                default:
                    Log.d(TAG, lifecycleEvent.getType() + "");
            }
        });

        mStompClient.topic("/topic/greetings").subscribe(topicMessage -> {
            Log.d("TAG", topicMessage.getPayload() + "gjregiherighirehgireger");
        });





    }

    @Override
    protected BasePresenter getPresenter() {
        return myDeviceListPresenter;
    }
}
