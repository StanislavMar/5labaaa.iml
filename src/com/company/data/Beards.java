package com.company.data;

public class Beards extends Animals {
    protected String Pereletn;


    public String getPerel() {
        return Pereletn;
    }

    public void setPerel(String perel) {
        Pereletn = perel;
    }

    public Beards(String name, int NumberVid, String Pereletn) {
        super(name, NumberVid);
        this.Pereletn = Pereletn;

    }
    @Override
    public String Info(){
        return ("Птица");
    }

}
