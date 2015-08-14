package com.sample.app.service;

import com.sample.app.dao.DeliveryDAO;
import com.sample.app.dao.OrderDAO;
import com.sample.app.dao.StorageDAO;
import com.sample.app.model.DeliveryShift;
import com.sample.app.model.OrderStatus;
import com.sample.app.model.db.Delivery;
import com.sample.app.model.db.Order;
import com.sample.app.model.db.Storage;
import com.sample.app.service.optimization.OptimizationStrategy;
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
    StorageDAO storageDAO;

    @Autowired
    GoogleGeoService googleGeoService;

    @Autowired
    OptimizationStrategy optimizationStrategy;

    public Delivery createDelivery(Date deliveryDate, DeliveryShift deliveryShift, String storageName) {
        Storage storage = storageDAO.findByName(storageName);

        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(deliveryDate);
        delivery.setDeliveryShift(deliveryShift);
        delivery.setStorage(storage);

        deliveryDAO.insert(delivery);

        return delivery;
    }

    public void add(Delivery delivery) {
        deliveryDAO.save(delivery);
    }

    public Delivery get(String id) {
        return deliveryDAO.findById(id);
    }

    public List<Delivery> getAll() {
        return deliveryDAO.findAll();
    }

    public void update(Delivery delivery) {
        deliveryDAO.save(delivery);
    }

    public void remove(String id) {
        deliveryDAO.remove(id);
    }
}
