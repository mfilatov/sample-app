package com.sample.app.service;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;
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
        GeocodingResult[] results =  GeocodingApi.geocode(geoApiContext, address).await();
        if (results == null || results.length == 0) {
            throw new Exception("Address not found");
        }

        return results[0].formattedAddress;
    }

    public DirectionsRoute getDirectionsRoute(String origin, String destination, String... wayPoints) throws Exception {
        DirectionsRoute[] results = DirectionsApi.newRequest(geoApiContext)
                .origin(origin)
                .destination(destination)
                .waypoints(wayPoints)
                .mode(TravelMode.DRIVING)
                .optimizeWaypoints(true)
                .alternatives(false)
                .await();

        if (results == null || results.length == 0) {
            throw new Exception("Route not found");
        }

        return results[0];
    }

    public DistanceMatrix getDistanceMatrix(String[] origins, String[] destinations) throws Exception {
        return DistanceMatrixApi.newRequest(geoApiContext)
                .origins(origins)
                .destinations(destinations)
                .mode(TravelMode.DRIVING).await();
    }
}
