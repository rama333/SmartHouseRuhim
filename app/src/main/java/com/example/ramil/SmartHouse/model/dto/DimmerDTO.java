package com.example.ramil.SmartHouse.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramil on 27.07.2016.
 */
public class DimmerDTO {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("brightValue")
        @Expose
        private int brightValue;
        @SerializedName("smoothMode")
        @Expose
        private int smoothMode;

        public int getBrightValue() {
            return brightValue;
        }

        public void setBrightValue(int brightValue) {
            this.brightValue = brightValue;
        }

        public int getSmoothMode() {
            return smoothMode;
        }

        public void setSmoothMode(int smoothMode) {
            this.smoothMode = smoothMode;
        }
    }
}
