package com.lost_bear.virustracker.models;

public class Location {

    private String state;
    private String country;
    private int latest_total_cases;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatest_total_cases() {
        return latest_total_cases;
    }

    public void setLatest_total_cases(int latest_total_cases) {
        this.latest_total_cases = latest_total_cases;
    }

    @Override
    public String toString() {
        return "Location{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latest_total_cases=" + latest_total_cases +
                '}';
    }
}
