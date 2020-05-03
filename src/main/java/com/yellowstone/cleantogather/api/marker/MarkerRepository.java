package com.yellowstone.cleantogather.api.marker;

import org.springframework.data.repository.CrudRepository;

// This will be auto implemented by Spring
// Crud refers to Create Read Update Delete
public interface MarkerRepository extends CrudRepository<Marker, Long> {
}
