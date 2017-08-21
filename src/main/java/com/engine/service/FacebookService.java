package com.engine.service;

import facebook4j.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wronskip on 20.08.2017.
 */
@Service
public class FacebookService {

    private Facebook facebook;

    /**
     * Contructor with setting AuthAccesstoken.
     */
    public FacebookService() {
        if(facebook==null){
            facebook = FacebookFactory.getSingleton();
            facebook.setOAuthAppId("1265875236865460", "2047e506ecd979dfb3ab9ecd08ddaa00");
            try {
                facebook.setOAuthAccessToken(facebook.getOAuthAppAccessToken());
            } catch (FacebookException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Search for places with given place name, country and city.
     * @param place
     * @param country
     * @param city
     * @return
     */
    public List<Place> searchRawPlaces(String place, String country, String city){
        ResponseList<Place> places = null;

        try {
            places = facebook.searchPlaces(place + " " + city + " " + country, new Reading().fields("location", "name"));
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return places.stream().collect(Collectors.toList());
    }
}
