package com.sample.app.dao;

import com.sample.app.model.db.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryDAO {
    @Autowired
    private MongoOperations mongoOperations;

    public void save(Delivery delivery) {
        mongoOperations.save(delivery);
    }

    public void insert(Delivery delivery) {
        mongoOperations.insert(delivery);
    }

    public Delivery findById(String id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), Delivery.class);
    }

    public List<Delivery> findAll() {
        return mongoOperations.findAll(Delivery.class);
    }

    public void remove(String id) {
        mongoOperations.remove(Query.query(Criteria.where("id").is(id)), Delivery.class);
    }
}
