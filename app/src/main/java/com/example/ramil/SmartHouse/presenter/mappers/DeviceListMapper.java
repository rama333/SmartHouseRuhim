package com.example.ramil.SmartHouse.presenter.mappers;

import com.example.ramil.SmartHouse.model.dto.DeviceDTO;
import com.example.ramil.SmartHouse.presenter.vo.Device;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Ramil on 02.08.2016.
 */
public class DeviceListMapper implements Func1<DeviceDTO, List<Device>> {
    @Override
    public List<Device> call(DeviceDTO deviceDTO) {
        List<Device> devices = new ArrayList<>();
        for(int i = 0; i < deviceDTO.getData().getDevices().size(); i++) {
            devices.add(new Device(deviceDTO.getData().getDevices().get(i).getId(),
                    deviceDTO.getData().getDevices().get(i).getDeviceType(),
                    deviceDTO.getData().getDevices().get(i).getCondition()));
        }
        return devices;
    }
}
