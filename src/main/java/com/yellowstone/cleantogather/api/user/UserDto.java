package com.yellowstone.cleantogather.api.user;

import com.yellowstone.cleantogather.api.event.EventInfo;

import java.util.List;

public class UserDto {

    private UserInfo userInfo;
    private List<EventInfo> events;

    public UserDto(UserInfo userInfo, List<EventInfo> events) {
        this.userInfo = userInfo;
        this.events = events;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<EventInfo> getEvents() {
        return events;
    }

    public void setEvents(List<EventInfo> events) {
        this.events = events;
    }
}
