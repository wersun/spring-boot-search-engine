package com.engine.pojos;

/**
 * Class which represents place with coordinates.
 * Created by wronskip on 19.08.2017.
 */
public class Place {

    private String name;
    private Float latitude;
    private Float longitude;

    /**
     * Contructor for empty place object.
     */
    public Place() {
    }

    /**
     * Constructor with parameters for place object.
     * @param name
     * @param latitude
     * @param longitude
     */
    public Place(String name, Float latitude, Float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
