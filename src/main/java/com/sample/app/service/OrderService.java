package com.sample.app.service;

import com.sample.app.dao.OrderDAO;
import com.sample.app.model.db.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    Converter converter;

    @Autowired
    OrderDAO orderDAO;

    public List<Order> loadOrders(InputStream inputStream) throws IOException {
        List<Order> orders = converter.convert(inputStream);
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {

            }
            orderDAO.insertAll(orders);
        }

        return orders;
    }
}
