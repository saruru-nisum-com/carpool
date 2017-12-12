package com.nisum.carpool.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan({"com.nisum"})
@EnableAutoConfiguration
@EnableCaching
@EnableCassandraRepositories(basePackages="com.nisum.carpool.data.repository")
@EnableScheduling
@Configuration
@PropertySource("classpath:application.properties")
public class PortalLauncher {
	
	public static void main(String[] args) {
		SpringApplication.run(PortalLauncher.class, args);
	}
}
