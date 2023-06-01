package com.example.apachepoi;

public class Materials {
    private String ID;
    private String Material;
    private String Humidity;
    private String ST;
    private String Carbon;
    private String Nitrogen;
    private String Phosphorus;

    public Materials(String ID, String Material, String Humidity, String ST, String Carbon, String Nitrogen, String Phosphorus){
        this.setID(ID);
        this.setMaterial(Material);
        this.setHumidity(Humidity);
        this.setST(ST);
        this.setCarbon(Carbon);
        this.setNitrogen(Nitrogen);
        this.setPhosphorus(Phosphorus);
    }
    public Materials(){

    }
    public Materials(String ID){

    }


    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public String getCarbon() {
        return Carbon;
    }

    public void setCarbon(String carbon) {
        Carbon = carbon;
    }

    public String getNitrogen() {
        return Nitrogen;
    }

    public void setNitrogen(String nitrogen) {
        Nitrogen = nitrogen;
    }

    public String getPhosphorus() {
        return Phosphorus;
    }

    public void setPhosphorus(String phosphorus) {
        Phosphorus = phosphorus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
