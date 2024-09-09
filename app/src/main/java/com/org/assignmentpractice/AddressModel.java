package com.org.assignmentpractice;

public class AddressModel {
    String street , suite , city , zipcode ;
    GeoAddessModel geoAddessModel;


//Constructor
    public AddressModel(String street, String suite, String city, String zipcode , GeoAddessModel geoAddessModel) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geoAddessModel = geoAddessModel;
    }


    public GeoAddessModel getGeoAddessModel() {
        return geoAddessModel;
    }

    public void setGeoAddessModel(GeoAddessModel geoAddessModel) {
        this.geoAddessModel = geoAddessModel;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
