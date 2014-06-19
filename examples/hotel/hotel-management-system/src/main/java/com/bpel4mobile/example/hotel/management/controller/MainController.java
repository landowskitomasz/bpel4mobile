package com.bpel4mobile.example.hotel.management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bpel4mobile.example.hotel.management.repository.RoomRepository;
import com.bpel4mobile.example.hotel.management.ws.CleanUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bpel4mobile.example.hotel.management.model.Category;
import com.bpel4mobile.example.hotel.management.model.CleanUpStatus;
import com.bpel4mobile.example.hotel.management.model.Room;

@Controller
public class MainController {

    @Autowired
    private CleanUpService cleanUpService;

    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping("/list")
    public String list(Model model) {

        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("history", convertObjectsToMaps(rooms));

        return "list";
    }

    private List<Map<String, Object>> convertObjectsToMaps(List<Room> rooms) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (Room room : rooms) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", room.getId());
            map.put("number", room.getNumber());
            map.put("floor", room.getFloor());
            map.put("category", room.getCategory().getName());
            map.put("status", room.getStatus().getName());
            maps.add(map);
        }
        return maps;
    }

    @RequestMapping("/form")
    public String form(Model model) {
        return "form";
    }

    @RequestMapping(value = "/startProcess", method = RequestMethod.POST)
    public String handleForm(@RequestParam("number") int number, @RequestParam("floor") int floor,
            @RequestParam("category") Category category) {

        Room room = new Room();
        room.setNumber(number);
        room.setFloor(floor);
        room.setStatus(CleanUpStatus.inProgress);
        room.setCategory(category);

        roomRepository.save(room);
        cleanUpService.startProcess(room);
        return "redirect:/list.html";
    }
}
