package com.evolvice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class EvolviceTask extends SpringBootServletInitializer implements WebApplicationInitializer {

	private final static Logger logger = LoggerFactory.getLogger(EvolviceTask.class);

	public static void main(String[] args) throws IOException {
		SpringApplication.run(EvolviceTask.class, args);
		System.out.println("Application Started");
		logger.info("Application Started");
	}
}