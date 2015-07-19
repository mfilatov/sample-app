package com.sample.app.model.db;

import com.sample.app.model.DeliveryShift;
import com.sample.app.model.OrderStatus;
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

    private OrderStatus orderStatus;

    private Long purchaseNumber;

    private Double volume;

    private Integer quantity;

    private Date deliveryDate;

    private DeliveryShift deliveryShift;

    private String rawData;

    private Address address;

    private Contact contact;

    @DBRef
    private Office office;

    public Order() {
    }

    @PersistenceConstructor
    public Order(OrderStatus orderStatus, Long purchaseNumber, double volume, int quantity, Date deliveryDate, DeliveryShift deliveryShift, String rawData, Address address, Contact contact, Office office) {
        this.orderStatus = orderStatus;
        this.purchaseNumber = purchaseNumber;
        this.volume = volume;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.deliveryShift = deliveryShift;
        this.rawData = rawData;
        this.address = address;
        this.contact = contact;
        this.office = office;
    }

    public String getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Long purchaseNumber) {
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryShift getDeliveryShift() {
        return deliveryShift;
    }

    public void setDeliveryShift(DeliveryShift deliveryShift) {
        this.deliveryShift = deliveryShift;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
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
