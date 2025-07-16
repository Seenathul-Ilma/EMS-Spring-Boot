package lk.ijse.gdse71.springboot_practice.controller;

import lk.ijse.gdse71.springboot_practice.dto.EventDTO;
import lk.ijse.gdse71.springboot_practice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 7/16/2025 12:50 PM
 * Project: SpringBoot_Practice
 * --------------------------------------------
 **/

@RequestMapping("api/v1/event")
@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("create")
    public void saveEvent(@RequestBody EventDTO eventDTO){
        eventService.saveEvent(eventDTO);
    }

    @PutMapping("edit")
    public void updateEvent(@RequestBody EventDTO eventDTO){
        eventService.updateEvent(eventDTO);
    }

    @GetMapping("allevents")
    public List<EventDTO> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PatchMapping("status/{id}")
    public void changeEventStatus(@PathVariable("id") Integer id){
        eventService.changeEventStatusById(id);
    }
}
