package com.example.ramil.SmartHouse.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramil on 02.08.2016.
 */
public class DeviceDTO {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("status")
    @Expose
    private String status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @SerializedName("data")
    @Expose
    private Data data;

    public class Data {
        @SerializedName("devicesDto")
        @Expose
        private List<Devices> devices = new ArrayList<>();

        public List<Devices> getDevices() {
            return devices;
        }

        public void setDevices(List<Devices> devices) {
            this.devices = devices;
        }
    }

    public class Devices {
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("deviceType")
        @Expose
        private String deviceType;
        @SerializedName("condition")
        @Expose
        private String condition;

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getCondition() {

            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


    }
}
