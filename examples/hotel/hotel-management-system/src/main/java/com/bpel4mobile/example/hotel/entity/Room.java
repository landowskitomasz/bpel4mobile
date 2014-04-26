package com.bpel4mobile.example.hotel.entity;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Room {

    private int number;

    private int floor;

    @ManyToOne
    private Category category;

    @Enumerated
    private RoomState state;
}
