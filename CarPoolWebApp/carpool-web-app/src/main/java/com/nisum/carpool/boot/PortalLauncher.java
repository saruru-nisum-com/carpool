package com.nisum.carpool.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


@SpringBootApplication
@ComponentScan({"com.nisum"})
@EnableAutoConfiguration
@EnableCaching
@EnableCassandraRepositories(basePackages="com.nisum.carpool.data.repository")
@Configuration
public class PortalLauncher {
	
	public static void main(String[] args) {
		SpringApplication.run(PortalLauncher.class, args);
	}
}
