package com.yellowstone.cleantogather.api.user;

import javax.persistence.*;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.*;

import com.yellowstone.cleantogather.api.event.Event;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
	@Column(unique = true, nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	@Size(min = 8, message = "Minimum password length: 8 characters")
	@JsonIgnore
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	List<Role> roles;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "userSubscribed")
	private Set<Event> eventSubscribed;
}
