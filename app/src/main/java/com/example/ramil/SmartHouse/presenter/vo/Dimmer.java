package com.example.ramil.SmartHouse.presenter.vo;

/**
 * Created by Ramil on 27.07.2016.
 */
public class Dimmer {

    private Data data = new Data(0,true,null);

    public Dimmer() {

    }

    public Data getData() {

        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{

    private int brightValue;
    private boolean smoothMode;
    private String type;

    public Data(int brightValue, boolean smoothMode, String type) {
        this.brightValue = brightValue;
        this.smoothMode = smoothMode;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBrightValue() {
        return brightValue;
    }

    public void setBrightValue(int brightValue) {
        this.brightValue = brightValue;
    }

    public boolean isSmoothMode() {
        return smoothMode;
    }

    public void setSmoothMode(boolean smoothMode) {
        this.smoothMode = smoothMode;
    }

}}
