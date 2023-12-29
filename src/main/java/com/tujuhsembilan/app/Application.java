package com.tujuhsembilan.app;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tujuhsembilan.app.model.Role;
import com.tujuhsembilan.app.model.User;
import com.tujuhsembilan.app.repository.RoleRepository;
import com.tujuhsembilan.app.repository.UserRepository;

@SpringBootApplication(scanBasePackages = { "com.tujuhsembilan.app", "lib.i18n" })
@EnableJpaAuditing

public class Application implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args){
		Role adminRole = roleRepository.findByRoleName("Admin");
		User adminAccount = userRepository.findFirstByRole(adminRole);
		if(adminAccount == null){
			User user = new User();
			user.setRole(adminRole);
			user.setEmail("admin@mail.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin12345"));
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setUsername("admin");
			userRepository.save(user);
		}
	}
}
