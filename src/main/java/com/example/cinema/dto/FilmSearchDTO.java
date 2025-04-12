package com.example.cinema.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.cinema.model.enums.Genre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для поиска фильма")
public class FilmSearchDTO {

    @Schema(description = "Название фильма", example = "Назад в будущее")
    private String filmTitle;

    @Schema(description = "Полное имя создателя фильма", example = "Роберт Земекис")
    private String filmCreatorFullName;

    @Schema(description = "Жанр фильма")
    private Genre genre;
}
