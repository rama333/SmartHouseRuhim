package com.example.ramil.SmartHouse.presenter.control;

import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.view.fragments.control.RosseteControlView;

/**
 * Created by Ramil on 08.10.2016.
 */

public class RosseteControlPresenter extends BasePresenter {

    RosseteControlView myFragmentDeviceRosseteView;

    public RosseteControlPresenter(RosseteControlView myFragmentDeviceRosseteView) {
        this.myFragmentDeviceRosseteView = myFragmentDeviceRosseteView;
    }

    public void startFragmentRossete(int id, String name) {

        myFragmentDeviceRosseteView.startRosseteFragment(id, name);
    }
}
