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

    private Integer orderNumber;

    private Double volume;

    private Integer quantity;

    private Date deliveryDate;

    private DeliveryShift deliveryShift;

    private String rawData;

    private String address;

    private Contact contact;

    @DBRef
    private Storage storage;

    public Order() {
    }

    @PersistenceConstructor
    public Order(OrderStatus orderStatus, Long purchaseNumber, Integer orderNumber, Double volume, Integer quantity, Date deliveryDate, DeliveryShift deliveryShift, String rawData, String address, Contact contact, Storage storage) {
        this.orderStatus = orderStatus;
        this.purchaseNumber = purchaseNumber;
        this.orderNumber = orderNumber;
        this.volume = volume;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.deliveryShift = deliveryShift;
        this.rawData = rawData;
        this.address = address;
        this.contact = contact;
        this.storage = storage;
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

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return purchaseNumber.equals(order.purchaseNumber);

    }

    @Override
    public int hashCode() {
        return purchaseNumber.hashCode();
    }
}
