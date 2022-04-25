package com.company.data;

public class ParnoKopit extends Animals {

    protected String Roga;

    public String getRoga() {
        return Roga;
    }

    public void setRoga(String roga) {
        Roga = roga;
    }

    public ParnoKopit (String name, int NumberVid, String Roga) {
        super(name, NumberVid);
        this.Roga = Roga;

    }

    @Override
    public String Info(){
        return ("Парнокопытное");
    }

}
