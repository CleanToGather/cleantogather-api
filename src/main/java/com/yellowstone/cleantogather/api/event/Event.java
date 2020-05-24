package com.yellowstone.cleantogather.api.event;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yellowstone.cleantogather.api.user.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/*
 * class Event
 */
@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String address;
    private LocalDateTime startDateTime;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name = "event_participants",
    		joinColumns = @JoinColumn(name = "event_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSubscribed;

    public Event() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(title, event.title) &&
                Objects.equals(description, event.description) &&
                Objects.equals(address, event.address) &&
                Objects.equals(startDateTime, event.startDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, address, startDateTime);
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

	public Set<User> getUserSubscribed() {
		return userSubscribed;
	}

	public void addUserSubscribed(User userSubscribing) {
		this.userSubscribed.add(userSubscribing);
	}
	
	public void deleteUserSubscribed(User userUnsubcribing) {
		this.userSubscribed.remove(userUnsubcribing);
	}
}
