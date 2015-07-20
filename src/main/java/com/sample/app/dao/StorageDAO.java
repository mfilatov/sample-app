package com.sample.app.dao;

import com.sample.app.model.db.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StorageDAO {
    @Autowired
    private MongoOperations mongoOperations;

    public Storage findById(String id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), Storage.class);
    }

    public Storage findByName(String name) {
        return mongoOperations.findOne(Query.query(Criteria.where("name").is(name)), Storage.class);
    }

    public List<Storage> findAll() {
        return mongoOperations.findAll(Storage.class);
    }
}
