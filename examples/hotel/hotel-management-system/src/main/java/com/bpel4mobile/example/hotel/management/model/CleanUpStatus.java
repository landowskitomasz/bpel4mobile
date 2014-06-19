package com.bpel4mobile.example.hotel.management.model;

/**
 * Created by tomaszlandowski on 15.06.2014.
 */
public enum CleanUpStatus {
    inProgress("W trakcie"), finished("Zakonczono");

    private final String name;

    private CleanUpStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
