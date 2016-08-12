package com.example.ramil.SmartHouse.model;

import com.example.ramil.SmartHouse.model.dto.DeviceDTO;
import com.example.ramil.SmartHouse.model.dto.OffOnDeviceDTO;
import com.example.ramil.SmartHouse.model.dto.RoomsDTO;
import com.example.ramil.SmartHouse.presenter.vo.Dimmer;

import rx.Observable;

/**
 * Created by Ramil on 03.06.2016.
 */
public interface Model {
    Observable<RoomsDTO> getRoomss();
    Observable<DeviceDTO> getDevice(int roomID);
    Observable<OffOnDeviceDTO> setAllBrightValue(int id, int BrightValue);
    Observable<OffOnDeviceDTO> setLevel(int id, Dimmer dimmer);
}
