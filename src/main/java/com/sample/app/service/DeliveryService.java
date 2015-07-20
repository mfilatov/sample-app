package com.sample.app.service;

import com.sample.app.dao.DeliveryDAO;
import com.sample.app.dao.OrderDAO;
import com.sample.app.model.DeliveryShift;
import com.sample.app.model.OrderStatus;
import com.sample.app.model.db.Delivery;
import com.sample.app.model.db.Order;
import com.sample.app.model.db.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    OrderDAO orderDAO;

    @Autowired
    DeliveryDAO deliveryDAO;

    @Autowired
    GoogleGeoService googleGeoService;

    public Delivery createDelivery(Date deliveryDate, DeliveryShift deliveryShift, Storage storage) {
        List<Order> orders = orderDAO.findByParams(deliveryDate, OrderStatus.READY, deliveryShift);

        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(deliveryDate);
        delivery.setDeliveryShift(deliveryShift);
        delivery.setStorage(storage);
        delivery.setOrders(orders);

        deliveryDAO.insert(delivery);

        return delivery;
    }
}
