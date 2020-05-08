package com.yellowstone.cleantogather.api.event;

import com.yellowstone.cleantogather.api.user.UserInfo;

import java.time.LocalDateTime;
import java.util.Set;

public class EventDto {

    private EventInfo eventInfo;
//    private Long id;
//    private String title;
//    private String description;
//    private String address;
//    private LocalDateTime startDateTime;
    private Set<UserInfo> userSubscribed;

    public EventDto(EventInfo eventInfo, Set<UserInfo> users) {
        this.eventInfo = eventInfo;
        this.userSubscribed = users;
    }

    public EventInfo getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(EventInfo eventInfo) {
        this.eventInfo = eventInfo;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public LocalDateTime getStartDateTime() {
//        return startDateTime;
//    }
//
//    public void setStartDateTime(LocalDateTime startDateTime) {
//        this.startDateTime = startDateTime;
//    }

    public Set<UserInfo> getUserSubscribed() {
        return userSubscribed;
    }

    public void setUserSubscribed(Set<UserInfo> userSubscribed) {
        this.userSubscribed = userSubscribed;
    }
}
