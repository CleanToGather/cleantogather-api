package com.yellowstone.cleantogather.api.event;

import java.time.LocalDateTime;

public class EventInfo {

    private Long id;
    private String title;
    private String description;
    private String address;
    private LocalDateTime startDateTime;

    public EventInfo(Long id, String title, String description, String address, LocalDateTime startDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.startDateTime = startDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
}
