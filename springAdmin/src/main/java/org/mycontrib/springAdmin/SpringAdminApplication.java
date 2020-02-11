package org.mycontrib.springAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer //de.codecentric.boot.admin.server.config.EnableAdminServer
public class SpringAdminApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringAdminApplication.class, args);
		System.out.println("http://localhost:8787/spring-admin");
	}

}
