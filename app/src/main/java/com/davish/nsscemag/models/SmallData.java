package com.davish.nsscemag.models;

public class SmallData {
    String name;
    String sColor;
    String eColor;

    public SmallData(String name, String sColor, String eColor, int direction) {
        this.name = name;
        this.sColor = sColor;
        this.eColor = eColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsColor() {
        return sColor;
    }

    public void setsColor(String sColor) {
        this.sColor = sColor;
    }

    public String geteColor() {
        return eColor;
    }

    public void seteColor(String eColor) {
        this.eColor = eColor;
    }

}
