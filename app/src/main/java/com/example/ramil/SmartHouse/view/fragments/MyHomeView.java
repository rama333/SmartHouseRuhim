package com.example.ramil.SmartHouse.view.fragments;

import com.example.ramil.SmartHouse.presenter.vo.Room;

import java.util.List;

/**
 * Created by Ramil on 26.07.2016.
 */
public interface MyHomeView extends View {

    void showList(List<Room> list);
    void ShowProgressDialog();
    void DismissProgressDialog();
    void startFragmentDevice(int id);
}
