package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country {
    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    // Empty parameter constructor with debug log
    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    // Getter for code with debug log
    public String getCode() {
        LOGGER.debug("Inside getCode() method. Returning code: {}", code);
        return code;
    }

    // Setter for code with debug log
    public void setCode(String code) {
        LOGGER.debug("Inside setCode() method. Setting code to: {}", code);
        this.code = code;
    }

    // Getter for name with debug log
    public String getName() {
        LOGGER.debug("Inside getName() method. Returning name: {}", name);
        return name;
    }

    // Setter for name with debug log
    public void setName(String name) {
        LOGGER.debug("Inside setName() method. Setting name to: {}", name);
        this.name = name;
    }

    // toString method
    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}