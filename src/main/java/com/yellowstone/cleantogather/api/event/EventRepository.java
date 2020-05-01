package com.yellowstone.cleantogather.api.event;

import org.springframework.data.repository.CrudRepository;

// This will be auto implemented by Spring
// Crud refers to Create Read Update Delete
public interface EventRepository extends CrudRepository<Event, Long> {
}
