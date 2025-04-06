package com.example.cinema.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO страны")
public class CountryDTO extends GenericDTO implements Comparable<CountryDTO>  {

    @Schema(description = "Страна", example = "Россия")
    private String countryName;

    @Override
    public int compareTo(CountryDTO o) {
            return countryName.compareTo(o.countryName);
    }
}
