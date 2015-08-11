package com.sample.app.service;

import com.sample.app.dao.OrderDAO;
import com.sample.app.model.db.Order;
import com.sample.app.service.converter.OrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderConverter orderConverter;

    @Autowired
    OrderDAO orderDAO;

    public List<Order> loadOrders(InputStream inputStream) throws IOException {
        List<Order> orders = orderConverter.convert(inputStream);
        if (orders != null && !orders.isEmpty()) {
            addAll(orders);
        }

        return orders;
    }

    public void add(Order order) {
        orderDAO.save(order);
    }

    public void addAll(List<Order> orders) {
        orderDAO.insertAll(orders);
    }

    public Order get(String id) {
        return orderDAO.findById(id);
    }

    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    public void update(Order order) {
        orderDAO.save(order);
    }

    public void remove(String id) {
        orderDAO.remove(id);
    }
}
