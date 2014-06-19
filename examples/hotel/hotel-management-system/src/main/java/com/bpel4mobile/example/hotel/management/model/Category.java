package com.bpel4mobile.example.hotel.management.model;

public enum Category {

    apartment("Apartament", 1), standard("Standard", 2);

    private final String name;

    private final int roomStandard;

    private Category(String name, int standard){
        this.name = name;
        this.roomStandard = standard;
    }

    public int getRoomStandard() {
        return roomStandard;
    }

    public String getName() {
        return name;
    }
}
