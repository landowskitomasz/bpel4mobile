package com.bpel4mobile.hotel.android.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by tomaszlandowski on 28.05.2014.
 */
public class Category {

    public static final String NAME = "name";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private int standard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
