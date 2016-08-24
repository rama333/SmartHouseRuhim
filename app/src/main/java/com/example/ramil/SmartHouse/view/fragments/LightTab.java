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

import com.example.ramil.SmartHouse.activity.ActivityCallback;
import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.MyDeviceListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.adapters.DeviceAdapter;

import org.java_websocket.WebSocket;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompHeader;
import ua.naiksoftware.stomp.client.StompClient;

/**
 * Created by Ramil on 30.07.2016.
 */
public class LightTab extends Fragment implements MyDeviceView {

    public static final int BRIGHT_VALUE_ON = 100;
    public static final int BRIGHT_VALUE_OFF = 0;
    public static final String TAG = "TAG";
    private final WebSocketConnection mConnection = new WebSocketConnection();
    private StompClient mStompClient;

    @Bind(R.id.rv)
    RecyclerView recyclerViewU;

    @Bind(R.id.On)
    TextView on;

    @Bind(R.id.Off)
    TextView off;

    private int id;

    private View view;

    public void setId(int id) {
        this.id = id;
    }

    private DeviceAdapter deviceAdapter;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private ActivityCallback activityCallback;

    private MyDeviceListPresenter myDeviceListPresenter = new MyDeviceListPresenter(this);

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

        deviceAdapter = new DeviceAdapter(new ArrayList<>(), getActivity(), myDeviceListPresenter, this);

        myDeviceListPresenter.ShowDevices(id);

        Log.d("TAG", String.valueOf(id) + " onCreate" + id);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL);
        //TODO helpers -> recyclerViewU.addItemDecoration(new SpacesItemDecoration(R.dimen.height));
        recyclerViewU.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewU.setHasFixedSize(true);
        recyclerViewU.setAdapter(deviceAdapter);

        on.setOnClickListener(view1 -> myDeviceListPresenter.OffOnDevices(id, BRIGHT_VALUE_ON));

        off.setOnClickListener(view1 -> myDeviceListPresenter.OffOnDevices(id, BRIGHT_VALUE_OFF));

        try {
            connectWebSocket();
        } catch (WebSocketException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void showList(List<Device> list) {
        deviceAdapter.setDeviceList(list);
    }

    @Override
    public void test(List<Device> list) {
        list.get(0).setType("q");
         deviceAdapter.notifyItemChanged(0);
    }

    @Override
    public void startFragmentDeviceControl(int id) {
        activityCallback.startFragmentDeviceControl(id);
    }

    @Override
    public void ShowError(String error) {
        Snackbar.make(recyclerViewU, error, Snackbar.LENGTH_LONG).show();

    }

    private void connectWebSocket() throws WebSocketException {

        final String wsuri = "ws://192.168.1.108:8070/ruhim/android";

        mStompClient = Stomp.over(WebSocket.class, "ws://192.168.1.110:8070/android/info");
        StompHeader taskIDHeader = new StompHeader("task-id", String.valueOf(5));
        StompHeader authTokenHeader = new StompHeader("Auth-Token", "vsdvs");
        List<StompHeader> headers = new ArrayList<>();
        headers.add(taskIDHeader);
        headers.add(authTokenHeader);
        mStompClient.connect();

        //mStompClient.send("", "My first STOMP message!").subscribe();

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

        mStompClient.topic("/sockets/android").subscribe(topicMessage -> {
            Log.d("TAG", topicMessage.getPayload());
        });





       // mStompClient.send("gerge");

        /*
        mConnection.connect(wsuri, new WebSocketConnectionHandler() {
            @Override
            public void onOpen() {
                Log.d("TAG", "Got echo: ");
                if (mConnection.isConnected()) {
                    Log.d("TAG", "ok ");
                    mConnection.sendTextMessage("vfv");
                    //ShowError("ok");
                } else Log.d("TAG", "error");

                Room room = new Room(1, "", "");
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                mConnection.sendTextMessage("vfv");

            }

            @Override
            public void onTextMessage(String payload) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Room murzik = gson.fromJson(payload, Room.class);

            }

            @Override
            public void onClose(int code, String reason) {
                Log.d("TAG", "Got echo: " + reason);
                //ShowError("ok and close" + reason);
                mConnection.reconnect();
                mConnection.sendTextMessage("vfv");
            }
        });*/
    }

    }
