package com.cognizant.spring_country_config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Starting Spring Country Configuration Application");

		// Create instance and call displayCountry method
		SpringLearnApplication app = new SpringLearnApplication();
		app.displayCountry();

		LOGGER.info("Application execution completed");
	}

	public void displayCountry() {
		LOGGER.debug("Inside displayCountry() method");

		// Load Spring configuration file
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

		// Get the country bean from Spring context
		com.cognizant.springlearn.Country country = context.getBean("country", com.cognizant.springlearn.Country.class);

		// Display country details
		LOGGER.debug("Country : {}", country.toString());

		// Also print to console for visibility
		System.out.println("Country Details: " + country.toString());

		// Close the context
		((ClassPathXmlApplicationContext) context).close();
	}
}