package com.bpel4mobile.example.hotel.web;

import com.bpel4mobile.example.hotel.entity.Room;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rooms")
@Controller
@RooWebScaffold(path = "rooms", formBackingObject = Room.class)
public class RoomController {
}
