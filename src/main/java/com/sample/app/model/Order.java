package com.sample.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = Order.COLLECTION_NAME)
public class Order {
    public static final String COLLECTION_NAME = "orders";

    @Id
    private String id;

    private String purchaseNumber;

    private double volume;

    private int quantity;

    private Date date;

    private Address address;

    private Contact contact;

    @DBRef
    private Office office;

    public Order() {
    }

    @PersistenceConstructor
    public Order(String purchaseNumber, double volume, int quantity, Date date, Address address, Contact contact, Office office) {
        this.purchaseNumber = purchaseNumber;
        this.volume = volume;
        this.quantity = quantity;
        this.date = date;
        this.address = address;
        this.contact = contact;
        this.office = office;
    }

    public String getId() {
        return id;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
