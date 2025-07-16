package lk.ijse.gdse71.springboot_practice.service.impl;

import lk.ijse.gdse71.springboot_practice.dto.EventDTO;
import lk.ijse.gdse71.springboot_practice.entity.Event;
import lk.ijse.gdse71.springboot_practice.repository.EventRepository;
import lk.ijse.gdse71.springboot_practice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> allEvents = eventRepository.findAll();
        return modelMapper.map(allEvents, new TypeToken<List<EventDTO>>() {}.getType());
    }

    @Override
    public void changeEventStatusById(Integer id) {
        eventRepository.updateEventStatusById(id);
    }

    @Override
    public List<EventDTO> getAllEventsByKeyword(String keyword) {
        List<Event> list = eventRepository.findEventsByEventStatusContainingIgnoreCaseOrderByEventDate(keyword);
        return modelMapper.map(list, new TypeToken<List<EventDTO>>() {}.getType());
    }
}
