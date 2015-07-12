package com.sample.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Order.COLLECTION_NAME)
public class Order {
    public static final String COLLECTION_NAME = "orders";

    @Id
    private String id;

    private String fullName;

    private String phoneNumber;

    private double volume;

    private int quatity;

    @DBRef
    private Address address;

    @DBRef
    private Office office;

    public Order() {
    }

    @PersistenceConstructor
    public Order(String fullName, String phoneNumber, double volume, int quatity, Address address, Office office) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.volume = volume;
        this.quatity = quatity;
        this.address = address;
        this.office = office;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
