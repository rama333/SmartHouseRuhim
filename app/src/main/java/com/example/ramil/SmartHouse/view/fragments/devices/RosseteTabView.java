package com.example.ramil.SmartHouse.view.fragments.devices;

import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.fragments.View;

import java.util.List;

/**
 * Created by Ramil on 29.08.2016.
 */
public interface RosseteTabView extends View {
    void showList(List<Device> list);
    void startFragmentRossete(int id, String name);
}
