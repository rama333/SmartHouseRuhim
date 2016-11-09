package com.example.ramil.SmartHouse.presenter.control;

import com.example.ramil.SmartHouse.model.dto.OffOnDeviceDTO;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.vo.Dimmer;
import com.example.ramil.SmartHouse.view.fragments.control.LightControlView;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Ramil on 11.08.2016.
 */
public class DeviceControlPresenter extends BasePresenter {

    LightControlView myDeviceControlView;

    public DeviceControlPresenter(LightControlView myDeviceControlView) {
        this.myDeviceControlView = myDeviceControlView;
    }

    public void setLevel(int id, Dimmer dimmer) {

        Subscription subscription = dataRepository.setLevel(id, dimmer)
                .subscribe(new Observer<OffOnDeviceDTO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myDeviceControlView.ShowError(e.getMessage());

                    }

                    @Override
                    public void onNext(OffOnDeviceDTO offOnDeviceDTO) {
                        myDeviceControlView.ShowError(offOnDeviceDTO.getStatus());

                    }
                });

        addSubscription(subscription);

    }
}
