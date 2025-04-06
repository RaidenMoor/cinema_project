package com.example.cinema.mapper;


import com.example.cinema.dto.CountryDTO;
import com.example.cinema.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper extends GenericMapper<Country, CountryDTO> {
    public CountryMapper() {
        super(Country.class, CountryDTO.class);
    }
}
