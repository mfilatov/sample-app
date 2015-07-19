package com.sample.app.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
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

    public String getFormattedAddress(String address) throws Exception {
        return address;
//        GeocodingResult[] results =  GeocodingApi.geocode(geoApiContext, address).await();
//
//        if (results == null || results.length == 0) {
//            throw new Exception("Address not found");
//        }
//
//        return results[0].formattedAddress;
    }

    public void getOptRoute() {
//        DirectionsApi.newRequest(geoApiContext).await()[0].waypointOrder
    }
}
