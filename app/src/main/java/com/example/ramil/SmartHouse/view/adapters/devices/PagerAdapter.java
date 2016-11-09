package com.example.ramil.SmartHouse.view.adapters.devices;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.ramil.SmartHouse.view.fragments.devices.RosseteTab;
import com.example.ramil.SmartHouse.view.fragments.devices.LightTab;

/**
 * Created by Ramil on 02.08.2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;
    private int id;

    public PagerAdapter(FragmentManager fm, int numOfTabs , int id) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                LightTab lightTab = new LightTab();
                lightTab.setId(id);
                Log.d("TAG", " pagger" + id);
                return lightTab;
            case 1:
                RosseteTab rosseteTab = new RosseteTab();
                rosseteTab.setId(id);
                return rosseteTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
