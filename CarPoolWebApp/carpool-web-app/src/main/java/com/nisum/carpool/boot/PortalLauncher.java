package com.nisum.carpool.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
@ComponentScan({"com.nisum"})
@EnableAutoConfiguration
@EnableCaching
public class PortalLauncher {
	
	public static void main(String[] args) {
		SpringApplication.run(PortalLauncher.class, args);
	}
}
