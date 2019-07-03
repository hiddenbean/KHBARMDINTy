package com.hiddenbean.android.khbarmdinty.resources;

import com.hiddenbean.android.khbarmdinty.models.Location;

import java.util.ArrayList;
import java.util.Map;

public class LocationsResource {

    private ArrayList<Location> data;
    private Map meta;

    public LocationsResource() { }

    public LocationsResource(ArrayList<Location> data, Map meta) {
        this.data = data;
        this.meta = meta;
    }

    public ArrayList<Location> getData() {
        return data;
    }

    public void setData(ArrayList<Location> data) {
        this.data = data;
    }

    public Map getMeta() {
        return meta;
    }

    public void setMeta(Map meta) {
        this.meta = meta;
    }
}
