package com.sample.app.service;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class GoogleGeoService {
    private final GeoApiContext geoApiContext;

    @Autowired
    public GoogleGeoService(@Value("${integration.google.apiKey}") String key) {
        geoApiContext = new GeoApiContext().setApiKey(key);
    }
}
