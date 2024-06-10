package org.example.model;

public class WifiInfo {
    private String name;
    private String location;
    private double longitude;
    private double latitude;
    private double distance;  // 거리 필드 추가

    public WifiInfo(String name, String location, double longitude, double latitude, double distance) {
        this.name = name;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getDistance() {
        return distance;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}