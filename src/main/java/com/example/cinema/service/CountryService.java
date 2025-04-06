package com.example.cinema.service;


import com.example.cinema.dto.CountryDTO;
import com.example.cinema.mapper.CountryMapper;
import com.example.cinema.model.Country;
import com.example.cinema.model.Film;
import com.example.cinema.repository.CountryRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CountryService extends GenericService<Country, CountryDTO>{
    public CountryService(CountryRepository countryRepository, CountryMapper countreMapper) {
        repository = countryRepository;
        mapper = countreMapper;
    }



    public List<CountryDTO> searchCountries(final String countryName) {
        List<Country> countries = ((CountryRepository) repository)
                .getAllByCountryNameContainsIgnoreCase(countryName);
        return mapper.toDTOs(countries);
    }

    @Override
    public void softDelete(Long id) {
        Country country = repository.findById(id).orElse(null);
        if (country != null) {
            country.setDeleted(true);
            country.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            country.setDeletedWhen(LocalDateTime.now());
            repository.save(country);
        }
    }
}
