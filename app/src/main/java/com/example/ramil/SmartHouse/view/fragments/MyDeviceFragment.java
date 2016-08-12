package com.example.ramil.SmartHouse.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.ActivityCallback;
import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.MyDeviceListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.adapters.DeviceAdapter;
import com.example.ramil.SmartHouse.view.adapters.PagerAdapter;

import java.util.List;

/**
 * Created by Ramil on 01.08.2016.
 */
public class MyDeviceFragment extends BaseFragment implements MyDeviceView {


    private DeviceAdapter deviceAdapter;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private MyDeviceListPresenter myDeviceListPresenter;

    private static final String BUNDLE_DEVICE = "BUNDLE_DEVICE";

    ActivityCallback activityCallback;

    public static MyDeviceFragment newInstance(int id) {
        MyDeviceFragment myDeviceFragment = new MyDeviceFragment();

        Bundle args = new Bundle();
        args.putInt(BUNDLE_DEVICE, id);
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

        Bundle b = getArguments();
        int id = b.getInt(BUNDLE_DEVICE);

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

    @Override
    public void showList(List<Device> list) {

    }

    @Override
    public void test(List<Device> list) {

    }

    @Override
    public void startFragmentDeviceControl() {

    }


    @Override
    public void ShowError(String error) {

    }
}
