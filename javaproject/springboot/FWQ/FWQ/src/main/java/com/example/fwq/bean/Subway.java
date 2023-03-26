package com.example.fwq.bean;

public class Subway {
    private String name;
    private int diatance;

    public Subway(String name, int diatance) {
        super();
        this.name = name;
        this.diatance = diatance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiatance() {
        return diatance;
    }

    public void setDiatance(int diatance) {
        this.diatance = diatance;
    }
}