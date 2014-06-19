package com.bpel4mobile.example.hotel.management.model;

import javax.persistence.*;

@Entity
public class Room {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int number;

    @Column
    private int floor;

    @Column
    @Enumerated
    private Category category;

    @Column
    @Enumerated
    private CleanUpStatus status;

    public int getId() { 
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CleanUpStatus getStatus() {
        return status;
    }

    public void setStatus(CleanUpStatus status) {
        this.status = status;
    }
}
