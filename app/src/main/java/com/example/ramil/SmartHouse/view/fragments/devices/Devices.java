package com.example.ramil.SmartHouse.view.fragments.devices;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.activity.ActivityCallback;
import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.view.adapters.devices.PagerAdapter;
import com.example.ramil.SmartHouse.view.fragments.BaseFragment;

/**
 * Created by Ramil on 01.08.2016.
 */
public class Devices extends BaseFragment {

    private static final String BUNDLE_DEVICE = "BUNDLE_DEVICE";
    private static final String BUNDLE_DEVICE_NAME_ACTIONBAR = "BUNDLE_DEVICE_NAME";

    ActivityCallback activityCallback;

    public static Devices newInstance(int id, String name) {
        Devices myDeviceFragment = new Devices();

        Log.d("TAG", " onCreate Bundle into" + id);
        Bundle args = new Bundle();
        args.putInt(BUNDLE_DEVICE, id);
        args.putString(BUNDLE_DEVICE_NAME_ACTIONBAR, name);
        myDeviceFragment.setArguments(args);

        return myDeviceFragment;
    }

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.my_device, container, false);

        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle b = getArguments();
        int id = b.getInt(BUNDLE_DEVICE);
        String name = b.getString(BUNDLE_DEVICE_NAME_ACTIONBAR);

        actionBar.setTitle(name);

        Log.d("TAG", " onCreate Bundle get" + id);

        Log.d("TAG", String.valueOf(id) + "device_fragment");

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("device"));
        tabLayout.addTab(tabLayout.newTab().setText("control"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount(), id);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

}