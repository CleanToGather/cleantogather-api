package com.yellowstone.cleantogather.api.user;

import org.springframework.data.repository.CrudRepository;

// This will be auto implemented by Spring
// Crud refers to Create Read Update Delete
public interface UserRepository extends CrudRepository<User, Long> {
}
