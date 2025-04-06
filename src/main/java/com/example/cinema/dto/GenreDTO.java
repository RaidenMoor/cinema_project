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
public class GenreDTO extends GenericDTO implements Comparable<GenreDTO>  {

    @Schema(description = "Страна", example = "Россия")
    private String genreName;

    @Override
    public int compareTo(GenreDTO o) {
        return genreName.compareTo(o.genreName);
    }
}
