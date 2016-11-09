package com.example.ramil.SmartHouse.presenter.devices;

import com.example.ramil.SmartHouse.model.dto.OffOnDeviceDTO;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.mappers.DeviceListMapper;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.fragments.devices.LightTabView;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Ramil on 02.08.2016.
 */
public class LightDeviceListPresenter extends BasePresenter {

    private static final String BUNDLE_MY_HOME_LIST_KEY = "BUNDLE_MY_HOME_LIST_KEY";

    private LightTabView myDeviceView;

    private DeviceListMapper deviceListMapper = new DeviceListMapper();

    private List<Device> devicesList;

    public LightDeviceListPresenter(LightTabView myDeviceView) {
        this.myDeviceView = myDeviceView;
    }

    public void ShowDevices(int id) {
        Subscription subscription = dataRepository.getDevice(id)
                .map(deviceListMapper)
                .subscribe(new Observer<List<Device>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myDeviceView.ShowError(e.getMessage() + " " + "error");
                    }

                    @Override
                    public void onNext(List<Device> devices) {
                        if (devices != null && !devices.isEmpty()) {
                                devicesList = devices;
                                myDeviceView.showList(devices);
                        }

                    }

                });
        addSubscription(subscription);
    }

    public void OffOnDevices(int id, int brightValue) {
        Subscription subscription = dataRepository.setAllBrightValue(id, brightValue)
                .subscribe(new Observer<OffOnDeviceDTO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myDeviceView.ShowError(e.getMessage() + "err");

                    }

                    @Override
                    public void onNext(OffOnDeviceDTO offOnDeviceDTO) {
                        myDeviceView.ShowError(offOnDeviceDTO.getStatus());

                    }
                });
        addSubscription(subscription);
    }

    public void startFragmentDeviceControl(int id, String name) {
        myDeviceView.startFragmentDeviceControl(id, name);
    }
    public void test(){ myDeviceView.test(devicesList);}

}