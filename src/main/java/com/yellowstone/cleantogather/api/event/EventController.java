package com.yellowstone.cleantogather.api.event;

import com.yellowstone.cleantogather.api.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller // This means that this class is a Controller
@RequestMapping(path="/events") // This means URL's start with /demo (after Application path)
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Event postEvent(@RequestParam String title, @RequestParam String description, @RequestParam String address, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime) {
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setAddress(address);
        event.setStartDateTime(startDateTime);
        return eventRepository.save(event);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable("id") Long id) {
        return eventRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Event deleteEvent(@PathVariable("id") Long id) {
        Event deletedEvent = eventRepository.findById(id).orElseThrow(NotFoundException::new);
        eventRepository.deleteById(id);
        return deletedEvent;
    }
}
