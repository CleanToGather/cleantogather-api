package com.yellowstone.cleantogather.api.event;

import com.yellowstone.cleantogather.api.common.exception.NotFoundException;
import com.yellowstone.cleantogather.api.user.User;
import com.yellowstone.cleantogather.api.user.UserInfo;
import com.yellowstone.cleantogather.api.user.UserRepository;

import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController // This means this class is a rest controller
@RequestMapping(path="/events") // This means URL's start with /events (after Application path)
public class EventController {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public EventController(EventRepository eventRepository, UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
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
    public List<EventDto> getAllEvents() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        return events
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @ApiOperation("Delete an event")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Event deleteEvent(@PathVariable("id") Long id) {
        Event deletedEvent = eventRepository.findById(id).orElseThrow(NotFoundException::new);
        eventRepository.deleteById(id);
        return deletedEvent;
    }
    
    @ApiOperation("Subscribe user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/subscribe")
    public Set<User> postSubscribeEvent(@RequestParam Long event_id, @RequestParam Long user_id) {
    	User userSubscribing = userRepository.findById(user_id).orElseThrow(NotFoundException::new);
    	Event event = eventRepository.findById(event_id).orElseThrow(NotFoundException::new);
    	event.addUserSubscribed(userSubscribing);
    	eventRepository.save(event);
    	return event.getUserSubscribed();
    }
    
    @ApiOperation("Get subscribed users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/subscribed/{id}")
    public Set<User> getSubscribedUsers(@PathVariable("id") Long id) {
    	Event event = eventRepository.findById(id).orElseThrow(NotFoundException::new);
    	return event.getUserSubscribed();
    }
    
    @ApiOperation("Unsubscribe user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/unsubscribe")
    public Set<User> postUnsubscribeEvent(@RequestParam Long event_id, @RequestParam Long user_id) {
    	User userSubscribing = userRepository.findById(user_id).orElseThrow(NotFoundException::new);
    	Event event = eventRepository.findById(event_id).orElseThrow(NotFoundException::new);
    	event.deleteUserSubscribed(userSubscribing);
    	eventRepository.save(event);
    	return event.getUserSubscribed();
    }

    private EventDto convertToDto(Event event) {
        EventInfo eventInfo = new EventInfo(event.getId(), event.getTitle(), event.getDescription(), event.getAddress(), event.getStartDateTime());
        Set<UserInfo> userSubscribed = new HashSet<>();
        if( event.getUserSubscribed() != null && event.getUserSubscribed().size() > 0) {
            for(User user : event.getUserSubscribed()) {
                UserInfo userInfo = new UserInfo(user.getId(), user.getName(), user.getRole(), user.getEmail(), user.getPassword());
                userSubscribed.add(userInfo);
            }
        }
        return new EventDto(eventInfo, userSubscribed);
    }
}
