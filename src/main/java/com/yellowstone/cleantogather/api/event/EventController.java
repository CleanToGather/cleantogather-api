package com.yellowstone.cleantogather.api.event;

import com.yellowstone.cleantogather.api.common.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means this class is a rest controller
@RequestMapping(path="/events") // This means URL's start with /events (after Application path)
public class EventController {

    private final EventRepository eventRepository;
    private final ModelMapper mapper;

    @Autowired
    public EventController(EventRepository eventRepository, ModelMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    @ApiOperation("Create a new event")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Event postEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @ApiOperation("Patch an existent event")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public Event patchEvent(@RequestBody Event event, @PathVariable("id") Long id) {
        Event eventToUpdate = eventRepository.findById(id).orElseThrow(NotFoundException::new);
        mapper.map(event, eventToUpdate);
        return eventRepository.save(eventToUpdate);
    }

    @ApiOperation("Get an event by his id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable("id") Long id) {
        return eventRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @ApiOperation("Get all the events")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @ApiOperation("Delete an event")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Event deleteEvent(@PathVariable("id") Long id) {
        Event deletedEvent = eventRepository.findById(id).orElseThrow(NotFoundException::new);
        eventRepository.deleteById(id);
        return deletedEvent;
    }
}
