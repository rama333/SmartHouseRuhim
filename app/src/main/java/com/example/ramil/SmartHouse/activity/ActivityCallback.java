package com.example.ramil.SmartHouse.activity;

/**
 * Created by Ramil on 02.08.2016.
 */
public interface ActivityCallback {
    void startFragmentDevice(int id, String name);
    void startFragmentDeviceControl(int id, String name);
    void startFragmentDeviceRosseteControl(int id, String name);
}
