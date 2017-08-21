package com.engine.webservice;

import com.engine.service.FacebookService;
import com.engine.service.SearchService;
import facebook4j.Place;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by wronskip on 21.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { FacebookService.class, SearchService.class})
public class SearchEngineWebServiceTest {



    @Autowired
    private FacebookService facebookService;

    @Autowired
    private SearchService searchService;


    @Test
    public void testFilterCorrect(){
        List<Place> places = facebookService.searchRawPlaces("egnyte", "poland","poznan");

        List<com.engine.pojos.Place> filteredPlaces = searchService.filterPlaces(places, "poznan", "poland");
        assertEquals(1,filteredPlaces.size());
        assertFalse(filteredPlaces.isEmpty());
        com.engine.pojos.Place foundPlace = filteredPlaces.get(0);
        assertEquals("Egnyte Poland",foundPlace.getName());
        assertEquals(52.40475, foundPlace.getLatitude(), 0.001);
        assertEquals(16.940681, foundPlace.getLongitude(), 0.001);
    }

    @Test
    public void testFindCorrectNumber(){
        List<Place> places = facebookService.searchRawPlaces("jatomi", "poland","poznan");
        assertEquals(3,places.size());
        assertFalse(places.isEmpty());
    }

    @Test
    public void testFilterIncorrect(){
        List<Place> places = facebookService.searchRawPlaces("ccacsafeewer", "poland","poznan");

        List<com.engine.pojos.Place> filteredPlaces = searchService.filterPlaces(places, "poznan", "poland");
        assertEquals(1,filteredPlaces.size());
        assertFalse(filteredPlaces.isEmpty());
        com.engine.pojos.Place foundPlace = filteredPlaces.get(0);
        assertEquals("Cannot find given place",foundPlace.getName());
        assertEquals(0.0, foundPlace.getLatitude(), 0.001);
        assertEquals(0.0, foundPlace.getLongitude(), 0.001);
    }
}
