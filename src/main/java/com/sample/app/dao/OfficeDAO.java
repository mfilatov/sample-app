package com.sample.app.dao;

import com.sample.app.model.db.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class OfficeDAO {
    @Autowired
    private MongoOperations mongoOperations;

    public void save(Office office) {
        mongoOperations.save(office);
    }

    public void insert(Office office) {
        mongoOperations.insert(office);
    }

    public void insertAll(Collection<Office> offices) {
        mongoOperations.insertAll(offices);
    }

    public Office findById(String id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), Office.class);
    }

    public Office findByName(String name) {
        return mongoOperations.findOne(Query.query(Criteria.where("name").is(name)), Office.class);
    }
    public List<Office> findAll() {
        return mongoOperations.findAll(Office.class);
    }

    public void remove(String id) {
        mongoOperations.remove(Query.query(Criteria.where("id").is(id)), Office.class);
    }

}
