package com.engine.webservice;

import com.engine.service.FacebookService;
import com.engine.service.SearchService;
import facebook4j.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wronskip on 19.08.2017.
 */
@RestController
public class SearchEngineWebService {

    @Autowired
    SearchService searchService;

    @Autowired
    FacebookService facebookService;




    @GetMapping(value = "/{country}/{city}/{place}")
    public List<com.engine.pojos.Place> getPlaces(
                            @PathVariable(value = "country") String country,
                            @PathVariable(value = "city") String city,
                            @PathVariable(value = "place") String place ){

        List<Place> places = facebookService.searchRawPlaces(place, city, country);
       return searchService.filterPlaces(places,city,country);
    }



}
