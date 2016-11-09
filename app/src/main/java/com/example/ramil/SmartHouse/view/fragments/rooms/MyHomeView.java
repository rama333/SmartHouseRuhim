package com.example.ramil.SmartHouse.view.fragments.rooms;

import com.example.ramil.SmartHouse.presenter.vo.Room;
import com.example.ramil.SmartHouse.view.fragments.View;

import java.util.List;

/**
 * Created by Ramil on 26.07.2016.
 */
public interface MyHomeView extends View {

    void showList(List<Room> list);
    void ShowProgressDialog();
    void DismissProgressDialog(boolean progress);
    void startFragmentDevice(int id, String name);
}
