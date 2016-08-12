package com.example.ramil.SmartHouse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.ramil.SmartHouse.view.fragments.MyDeviceControlFragment;
import com.example.ramil.SmartHouse.view.fragments.MyDeviceFragment;
import com.example.ramil.SmartHouse.view.fragments.MyHomeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

/**
 * Created by Ramil on 26.07.2016.
 */
public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private final static String TAG = "TAG";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private final WebSocketConnection mConnection = new WebSocketConnection();

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) replaceFragment(new MyHomeFragment(), false);

        Log.d("TAG", "test");
        connectWebSocket();
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void startFragmentDevice(int id) {
        replaceFragment(MyDeviceFragment.newInstance(id), true);
    }

    @Override
    public void startFragmentDeviceControl() {
        replaceFragment(MyDeviceControlFragment.newInstance(), true);
    }

    private void connectWebSocket() {

        final String wsuri = "ws://http://stage.ruhim.fsep-lab.ru:8080/ruhim_demo/android";

        try {
            mConnection.connect(wsuri, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    Log.d(TAG, "Status: Connected to " + wsuri);
                    mConnection.sendTextMessage("Hello, world!");
                }

                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "Got echo: " + payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "Connection lost.");
                }
            });
        } catch (WebSocketException e) {

            Log.d(TAG, e.toString());
        }

    }

}
