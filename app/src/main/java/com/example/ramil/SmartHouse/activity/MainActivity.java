package com.example.ramil.SmartHouse.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.view.fragments.MyDeviceControlFragment;
import com.example.ramil.SmartHouse.view.fragments.MyDeviceFragment;
import com.example.ramil.SmartHouse.view.fragments.MyHomeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ramil on 26.07.2016.
 */
public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private final static String TAG = "TAG";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

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
    public void startFragmentDeviceControl(int id) {
        replaceFragment(MyDeviceControlFragment.newInstance(id), true);
    }
}
