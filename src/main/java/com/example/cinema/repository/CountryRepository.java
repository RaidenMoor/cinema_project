package com.example.cinema.repository;


import com.example.cinema.model.Country;

import java.util.List;

public interface CountryRepository extends GenericRepository<Country> {
    List<Country> getAllByCountryNameContainsIgnoreCase(String countryName);
}
