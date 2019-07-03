package com.hiddenbean.android.khbarmdinty.models;

public class Location {
    private String name;
    private long longitude;
    private long latitude;
    private boolean isSelected;

    public Location() { }

    public Location(String name, long longitude, long latitude, boolean is_selected) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isSelected = is_selected;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean is_selected) {
        this.isSelected = is_selected;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public long getLatitude() {
        return latitude;
    }
}
