package com.aliyayman.googlefindplacesapp;

public class Places {
  private   String name;
  private   double latitude;
  private   double meridian;
  private   String adress;

    public Places() {
    }

    public Places(String name, double latitude, double meridian, String adress) {
        this.name = name;
        this.latitude = latitude;
        this.meridian = meridian;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getMeridian() {
        return meridian;
    }

    public void setMeridian(double meridian) {
        this.meridian = meridian;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
