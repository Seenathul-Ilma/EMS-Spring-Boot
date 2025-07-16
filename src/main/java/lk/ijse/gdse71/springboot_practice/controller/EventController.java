package lk.ijse.gdse71.springboot_practice.controller;

import lk.ijse.gdse71.springboot_practice.dto.EventDTO;
import lk.ijse.gdse71.springboot_practice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
