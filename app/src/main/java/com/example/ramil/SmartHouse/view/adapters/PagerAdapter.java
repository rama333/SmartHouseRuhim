package com.example.ramil.SmartHouse.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ramil.SmartHouse.view.fragments.ControlLightTab;
import com.example.ramil.SmartHouse.view.fragments.LightTab;

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
                return lightTab;
            case 1:
                ControlLightTab controlLightTab = new ControlLightTab();
                return controlLightTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
