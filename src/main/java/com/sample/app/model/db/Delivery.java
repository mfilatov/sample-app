package com.sample.app.model.db;

import com.sample.app.model.DeliveryShift;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = Delivery.COLLECTION_NAME)
public class Delivery {
    public static final String COLLECTION_NAME = "deliveries";

    @Id
    private String id;

    private Date deliveryDate;

    private DeliveryShift deliveryShift;

    @DBRef
    private Storage storage;

    @DBRef
    List<Order> orders;

    public Delivery() {
    }

    public Delivery(Date deliveryDate, DeliveryShift deliveryShift, Storage storage, List<Order> orders) {
        this.deliveryDate = deliveryDate;
        this.deliveryShift = deliveryShift;
        this.storage = storage;
        this.orders = orders;
    }

    public String getId() {
        return id;
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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delivery delivery = (Delivery) o;

        if (!deliveryDate.equals(delivery.deliveryDate)) return false;
        if (deliveryShift != delivery.deliveryShift) return false;
        return storage.equals(delivery.storage);

    }

    @Override
    public int hashCode() {
        int result = deliveryDate.hashCode();
        result = 31 * result + deliveryShift.hashCode();
        result = 31 * result + storage.hashCode();
        return result;
    }
}
