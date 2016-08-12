package com.example.ramil.SmartHouse.view.fragments;

import com.example.ramil.SmartHouse.presenter.vo.Device;

import java.util.List;

/**
 * Created by Ramil on 02.08.2016.
 */
public interface MyDeviceView extends View {
    void showList(List<Device> list);
    void test(List<Device> list);
    void startFragmentDeviceControl();
}
