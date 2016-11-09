package com.example.ramil.SmartHouse.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.view.fragments.control.RosseteControl;
import com.example.ramil.SmartHouse.view.fragments.control.LightControl;
import com.example.ramil.SmartHouse.view.fragments.devices.Devices;
import com.example.ramil.SmartHouse.view.fragments.rooms.MyHomeFragment;

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
    public void startFragmentDevice(int id, String name) {
        replaceFragment(Devices.newInstance(id, name), true);
    }

    @Override
    public void startFragmentDeviceControl(int id, String name) {
        replaceFragment(LightControl.newInstance(id, name), true);
    }

    @Override
    public void startFragmentDeviceRosseteControl(int id, String name) {
        replaceFragment(RosseteControl.newInstance(id, name), true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.setting:
                Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
                return true;

        }
            return (super.onOptionsItemSelected(menuItem));
    }


}
