package com.sample.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = Address.COLLECTION_NAME)
public class Address implements Serializable {
    public static final String COLLECTION_NAME = "addresses";

    @Id
    private String id;

    private String apt;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String country;

    public Address() {
    }

    @PersistenceConstructor
    public Address(String apt, String street, String city, String state, String zip, String country) {
        this.apt = apt;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
