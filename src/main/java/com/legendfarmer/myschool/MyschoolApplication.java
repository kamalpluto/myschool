package com.legendfarmer.myschool;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.legendfarmer.myschool.config.SecurityUtility;
import com.legendfarmer.myschool.domain.User;
import com.legendfarmer.myschool.domain.security.Role;
import com.legendfarmer.myschool.domain.security.UserRole;
import com.legendfarmer.myschool.service.UserService;

@SpringBootApplication
//@ComponentScan({"com.legendfarmer.myschool.service"})
@ComponentScan({"com.legendfarmer.myschool.repository", "com.legendfarmer.myschool.domain.security", "com.legendfarmer.myschool.domain", "com.legendfarmer.myschool.service", "com.legendfarmer.myschool.service.impl",
	"com.legendfarmer.myschool.service.impl.UserService"})
//@EntityScan("com.legendfarmer.myschool.domain")
//@EnableJpaRepositories("com.legendfarmer.myschool.repository")
public class MyschoolApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MyschoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Kamal");
		user1.setLastName("Kannan S");
		user1.setUsername("kamalpluto");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));		
		user1.setEmail("kamalpluto@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("admin");
		
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
		
		userRoles.clear();
		
		User user2 = new User();
		user2.setFirstName("admin");
		user2.setLastName("admin");
		user2.setUsername("admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));		
		user2.setEmail("admin@gmail.com");
		Role role2 = new Role();
		role2.setRoleId(0);
		role2.setName("Role_admin");
		
		userRoles.add(new UserRole(user2, role2));
		
		userService.createUser(user2, userRoles);
	}
}
