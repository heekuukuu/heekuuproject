package org.example;

public class WifiInfo {
    private int id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String installedDate;

    // 생성자
    public WifiInfo() {
    }

    // 모든 필드를 포함하는 생성자
    public WifiInfo(int id, String name, String address, double latitude, double longitude, String installedDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.installedDate = installedDate;
    }

    // Getter와 Setter 메소드
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(String installedDate) {
        this.installedDate = installedDate;
    }
}
