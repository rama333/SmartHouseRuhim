package com.example.ramil.SmartHouse.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.ramil.SmartHouse.view.fragments.ControlLightTab;
import com.example.ramil.SmartHouse.view.fragments.LightTab;

/**
 * Created by Ramil on 02.08.2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;
    int id;


    public PagerAdapter(FragmentManager fm, int numOfTabs , int id) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.id = id;
        Log.d("TAG", String.valueOf(id) + "pager");
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                LightTab lightTab = new LightTab();
                Log.d("TAG", String.valueOf(id) + "pager getItem");
                lightTab.setId(id);
                return lightTab;
            case 1:
                ControlLightTab controlLightTab = new ControlLightTab();
                return controlLightTab;
            default:
                Log.d("TAG", String.valueOf(id) + "pager getItem");
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
