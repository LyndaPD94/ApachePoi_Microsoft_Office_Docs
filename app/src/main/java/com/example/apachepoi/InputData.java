package com.example.apachepoi;

public class InputData extends XDDFValueAxis {
    private  Float ten;
    private  Float two;
    private  Float three;
    private  Float eleven;

    public InputData(Float ten, Float two,Float three,Float eleven){
      setTen(ten);
      setThree(three);
      setEleven(eleven);
      setTwo(two);
    }
    public  InputData(){

    }

    public Float getTen() {
        ten=Float.parseFloat(String.valueOf(10));
        return ten;
    }

    public void setTen(Float ten) {
        this.ten = ten;
    }

    public Float getTwo() {
        two=Float.parseFloat(String.valueOf(2));
        return two;
    }

    public void setTwo(Float two) {
        this.two = two;
    }

    public Float getThree() {
        three=Float.parseFloat(String.valueOf(3));
        return three;
    }

    public void setThree(Float three) {
        this.three = three;
    }

    public Float getEleven() {
        eleven=Float.parseFloat(String.valueOf(11));
        return eleven;
    }

    public void setEleven(Float eleven) {
        this.eleven = eleven;
    }
}