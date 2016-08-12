package com.example.ramil.SmartHouse.presenter.vo;

import java.io.Serializable;

/**
 * Created by Ramil on 03.06.2016.
 */
public class Branch implements Serializable {

    private String name;

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        return !(name != null ? !name.equals(branch.name) : branch.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
