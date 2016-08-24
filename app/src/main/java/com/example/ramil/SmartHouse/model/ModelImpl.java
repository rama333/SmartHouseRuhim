package com.example.ramil.SmartHouse.model;

import com.example.ramil.SmartHouse.Config;
import com.example.ramil.SmartHouse.model.api.ApiInterface;
import com.example.ramil.SmartHouse.model.api.ApiModule;
import com.example.ramil.SmartHouse.model.dto.DeviceDTO;
import com.example.ramil.SmartHouse.model.dto.OffOnDeviceDTO;
import com.example.ramil.SmartHouse.model.dto.RoomsDTO;
import com.example.ramil.SmartHouse.presenter.vo.Dimmer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ramil on 03.06.2016.
 */
public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;
    ApiInterface apiInterface = ApiModule.getApiInterface(Config.ENDPOINT_RELEASE);

    public ModelImpl() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
        ;
    }

    @Override
    public Observable<RoomsDTO> getRoomss() {
        return apiInterface
                .getRooms()
                .compose(applySchedulers());
    }

    @Override
    public Observable<DeviceDTO> getDevice(int roomID) {
        return apiInterface
                .getDevices(roomID)
                .compose(applySchedulers());
    }

    @Override
    public Observable<OffOnDeviceDTO> setAllBrightValue(int id, int brightValue) {
        return apiInterface
                .setAllBrightValue(id, brightValue)
                .compose(applySchedulers());
    }

    @Override
    public Observable<OffOnDeviceDTO> setLevel(int id, Dimmer dimmer) {
        return apiInterface
                .setLevel(id, dimmer)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
