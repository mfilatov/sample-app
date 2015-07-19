package com.sample.app.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = Office.COLLECTION_NAME)
public class Office implements Serializable {
    public static final String COLLECTION_NAME = "offices";

    @Id
    private String id;

    private String name;

    private String address;

    public Office() {
    }

    @PersistenceConstructor
    public Office(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
