package com.example.ramil.SmartHouse.view.fragments.devices;

import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.fragments.View;

import java.util.List;

/**
 * Created by Ramil on 02.08.2016.
 */
public interface LightTabView extends View {
    void showList(List<Device> list);
    void test(List<Device> list);
    void startFragmentDeviceControl(int id, String name);
}
