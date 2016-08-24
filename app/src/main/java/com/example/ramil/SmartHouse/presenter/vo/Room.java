package com.example.ramil.SmartHouse.presenter.vo;

import java.io.Serializable;

/**
 * Created by Ramil on 27.07.2016.
 */
public class Room implements Serializable {

    private int id;
    private String url;
    private String name;

    public Room(int id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
