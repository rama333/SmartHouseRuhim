package com.example.ramil.SmartHouse.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramil on 10.08.2016.
 */
public class OffOnDeviceDTO {
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
        @SerializedName("id")
        @Expose
        int id;
        @SerializedName("brightValue")
        @Expose
        int brightValue;
    }

}
