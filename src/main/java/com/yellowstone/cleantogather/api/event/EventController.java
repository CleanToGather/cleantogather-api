package com.yellowstone.cleantogather.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/events") // This means URL's start with /demo (after Application path)
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="")
    public Event postEvent(@RequestParam String title, @RequestParam String description, @RequestParam String address, @RequestParam LocalDateTime startDateTime) {
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setAddress(address);
        event.setStartDateTime(startDateTime);
        return eventRepository.save(event);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }
}
