package com.example.ramil.SmartHouse.presenter.devices;

import com.example.ramil.SmartHouse.model.dto.OffOnDeviceDTO;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.mappers.DeviceListMapper;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.fragments.devices.RosseteTabView;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Ramil on 29.08.2016.
 */
public class RosseteDeviceListPresenter extends BasePresenter {

    private RosseteTabView myDeviceRosseteView;

    private DeviceListMapper deviceListMapper = new DeviceListMapper();

    private List<Device> devicesList;

    public RosseteDeviceListPresenter(RosseteTabView myDeviceRosseteView) {
        this.myDeviceRosseteView = myDeviceRosseteView;
    }

    public void ShowDevices(int id) {
        Subscription subscription = dataRepository.getDeviceRossete(id)
                .map(deviceListMapper)
                .subscribe(new Observer<List<Device>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myDeviceRosseteView.ShowError(e.getMessage() + " " + "error");
                    }

                    @Override
                    public void onNext(List<Device> devices) {
                        if (devices != null && !devices.isEmpty()) {
                            devicesList = devices;
                            myDeviceRosseteView.showList(devices);
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
                        myDeviceRosseteView.ShowError(e.getMessage() + "err");

                    }

                    @Override
                    public void onNext(OffOnDeviceDTO offOnDeviceDTO) {
                        myDeviceRosseteView.ShowError(offOnDeviceDTO.getStatus());

                    }
                });
        addSubscription(subscription);
    }

    public void startFragmentRosseteControl(int id, String name) {
        myDeviceRosseteView.startFragmentRossete(id, name);
    }
}
