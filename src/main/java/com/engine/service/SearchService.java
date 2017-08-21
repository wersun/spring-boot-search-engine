package com.engine.service;

import com.engine.pojos.Place;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wronskip on 20.08.2017.
 */
@Service
public class SearchService {

    /**
     * Method filter raw places from facebook graph api and creates new list of place objects.
     * @param rawPlaces
     * @param city
     * @param country
     * @return
     */
    public List<Place> filterPlaces(List<facebook4j.Place> rawPlaces, String city, String country) {
        List<Place> resultList = new ArrayList<>();

        List<facebook4j.Place> filteredRawPlaces = rawPlaces.stream()
                .filter(rawPlace -> rawPlace.getLocation().getCountry()!=null &&rawPlace.getLocation().getCountry().equalsIgnoreCase(country))
                .filter(rawPlace ->rawPlace.getLocation().getCity()!=null && rawPlace.getLocation().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

        if(filteredRawPlaces.isEmpty()){
            Place place = new Place("Cannot find given place", 0.0F, 0.0F);
            resultList.add(place);
            return resultList;
        }

        filteredRawPlaces.forEach(rawPlace->{
            Place place = new Place();
            place.setName(rawPlace.getName());
            place.setLatitude(rawPlace.getLocation().getLatitude().floatValue());
            place.setLongitude(rawPlace.getLocation().getLongitude().floatValue());
            resultList.add(place);
        });

        return resultList;

    }



}
