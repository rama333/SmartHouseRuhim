package com.example.ramil.SmartHouse.presenter.vo;

/**
 * Created by Ramil on 02.08.2016.
 */
public class Device {

    private int id;

    private String type;

    private String condition;

    public Device(int id, String type, String condition) {

        this.id = id;
        this.type = type;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (id != device.id) return false;
        if (type != null ? !type.equals(device.type) : device.type != null) return false;
        return !(condition != null ? !condition.equals(device.condition) : device.condition != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }
}
