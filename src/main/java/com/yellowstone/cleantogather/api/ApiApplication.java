package com.yellowstone.cleantogather.api;

import com.yellowstone.cleantogather.api.user.Role;
import com.yellowstone.cleantogather.api.user.User;
import com.yellowstone.cleantogather.api.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class ApiApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... params) throws Exception {
		User admin = new User();
		admin.setName("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		userService.signup(admin);

		User user = new User();
		user.setName("user");
		user.setPassword("user");
		user.setEmail("user@email.com");
		user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_USER)));

		userService.signup(user);
	}
}
