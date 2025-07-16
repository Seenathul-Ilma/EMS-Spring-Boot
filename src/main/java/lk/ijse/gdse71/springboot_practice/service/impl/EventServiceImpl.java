package lk.ijse.gdse71.springboot_practice.service.impl;

import lk.ijse.gdse71.springboot_practice.dto.EventDTO;
import lk.ijse.gdse71.springboot_practice.entity.Event;
import lk.ijse.gdse71.springboot_practice.repository.EventRepository;
import lk.ijse.gdse71.springboot_practice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 7/16/2025 12:56 PM
 * Project: SpringBoot_Practice
 * --------------------------------------------
 **/

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveEvent(EventDTO eventDTO) {
        eventRepository.save(modelMapper.map(eventDTO, Event.class));
    }

    @Override
    public void updateEvent(EventDTO eventDTO) {
        eventRepository.save(modelMapper.map(eventDTO, Event.class));
    }
}
