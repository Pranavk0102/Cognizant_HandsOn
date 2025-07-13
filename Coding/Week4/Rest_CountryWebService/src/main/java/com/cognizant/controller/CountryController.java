package com.cognizant.controller;

import com.cognizant.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountryIndia() {
        // Load India bean from spring xml configuration
        Country india = (Country) applicationContext.getBean("indiaCountry");
        return india;
    }
}