package com.cognizant.spring_country.service;

import com.cognizant.spring_country.model.Country;
import com.cognizant.spring_country.model.CountryList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    public Country getCountry(String code) {
        try {
            List<Country> countries = getCountriesFromXml();

            // Using lambda expression for case insensitive matching
            Optional<Country> country = countries.stream()
                    .filter(c -> c.getCode().equalsIgnoreCase(code))
                    .findFirst();

            return country.orElse(null);

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving country: " + e.getMessage(), e);
        }
    }

    private List<Country> getCountriesFromXml() throws JAXBException, IOException {
        ClassPathResource resource = new ClassPathResource("country.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(CountryList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CountryList countryList = (CountryList) unmarshaller.unmarshal(resource.getInputStream());
        return countryList.getCountries();
    }
}

