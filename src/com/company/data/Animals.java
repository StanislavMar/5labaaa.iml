package com.company.data;

public abstract class Animals {

    protected String name;
    protected int NumberVid;

    public int getNumberVid() {
        return NumberVid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberVid(int numberVid) {
        NumberVid = numberVid;
    }

    public Animals(String name, int NumberVid) {
        this.name = name;
        this.NumberVid = NumberVid;
    }

    public abstract String Info();
}
