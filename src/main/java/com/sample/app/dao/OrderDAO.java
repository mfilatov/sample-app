package com.sample.app.dao;

import com.sample.app.model.db.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class OrderDAO {
    @Autowired
    private MongoOperations mongoOperations;

    public void save(Order order) {
        mongoOperations.save(order);
    }

    public void insert(Order order) {
        mongoOperations.insert(order);
    }

    public void insertAll(Collection<Order> orders) {
        mongoOperations.insertAll(orders);
    }

    public Order findById(String id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), Order.class);
    }

    public List<Order> findAll() {
        return mongoOperations.findAll(Order.class);
    }

    public void remove(String id) {
        mongoOperations.remove(Query.query(Criteria.where("id").is(id)), Order.class);
    }
}
