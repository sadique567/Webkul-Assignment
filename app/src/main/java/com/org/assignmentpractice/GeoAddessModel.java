package com.org.assignmentpractice;

public class GeoAddessModel {
    double latitudeAdd , Longitude;

    public GeoAddessModel(double latitudeAdd, double longitude) {
        this.latitudeAdd = latitudeAdd;
        Longitude = longitude;
    }

    public double getLatitudeAdd() {
        return latitudeAdd;
    }

    public void setLatitudeAdd(double latitudeAdd) {
        this.latitudeAdd = latitudeAdd;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
