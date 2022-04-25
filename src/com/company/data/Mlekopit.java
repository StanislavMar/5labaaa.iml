package com.company.data;

public class Mlekopit extends Animals
{

    protected String Skinn;

    public String getSkinn() {
        return Skinn;
    }

    public void setSkinn(String skinn) {
        Skinn = skinn;
    }

    public Mlekopit  (String name, int NumberVid, String Skinn)
    {
        super(name, NumberVid);
        this.Skinn = Skinn;

    }
    @Override
    public String Info(){
        return ("Млекопитающее");
    }

}
