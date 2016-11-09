package com.example.ramil.SmartHouse.model.api;

import com.example.ramil.SmartHouse.model.dto.DeviceDTO;
import com.example.ramil.SmartHouse.model.dto.OffOnDeviceDTO;
import com.example.ramil.SmartHouse.model.dto.RoomsDTO;
import com.example.ramil.SmartHouse.presenter.vo.Dimmer;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Ramil on 03.06.2016.
 */
public interface ApiInterface {
    @GET("rooms/")
    Observable<RoomsDTO> getRooms();

    @GET("rooms/{room-id}/dimmers")
    Observable<DeviceDTO> getDevices(@Path("room-id") int deviceId);

    @GET("rooms/{room-id}/rosettes")
    Observable<DeviceDTO> getDevicesRossete(@Path("room-id") int deviceId);

    @POST("rooms/{room-id}/light")
    Observable<OffOnDeviceDTO> setAllBrightValue(@Path("room-id") int deviceId, @Query("brightValue") int brightValue);

    @POST("dimmers/{id}")
    Observable<OffOnDeviceDTO> setLevel(@Path("id") int deviceId, @Body Dimmer dimmer);
}
