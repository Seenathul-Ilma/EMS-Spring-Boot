package lk.ijse.gdse71.springboot_practice.service;

import lk.ijse.gdse71.springboot_practice.dto.EventDTO;

public interface EventService {
    void saveEvent(EventDTO eventDTO);
    void updateEvent(EventDTO eventDTO);
}
