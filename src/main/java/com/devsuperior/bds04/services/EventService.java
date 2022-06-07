package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable) {
        Page<Event> list = repository.findAll(pageable);
        return list.map(x -> new EventDTO(x));
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event event = new Event();
        event.setName(dto.getName());
        event.setCity(new City(dto.getCityId(), null));
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        repository.save(event);
        return new EventDTO(event);
    }
}
