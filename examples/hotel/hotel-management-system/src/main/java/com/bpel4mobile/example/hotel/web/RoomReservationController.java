package com.bpel4mobile.example.hotel.web;

import com.bpel4mobile.example.hotel.entity.RoomReservation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/roomreservations")
@Controller
@RooWebScaffold(path = "roomreservations", formBackingObject = RoomReservation.class)
public class RoomReservationController {
}
