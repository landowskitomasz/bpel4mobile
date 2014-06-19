package com.bpel4mobile.example.hotel.management.model;

public enum CleanUpStatus {
    inProgress("W trakcie"), finished("Zakonczono"), failed("B³¹d");

    private final String name;

    private CleanUpStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
