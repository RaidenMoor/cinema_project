package com.example.cinema.dto;

import com.example.cinema.model.HallType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO ЗАЛА")
public class HallDTO extends GenericDTO {

    @Schema(description = "Количество рядов")
    @Min(value = 5, message = "Необходимо добавить хотя бы 3 ряда")
    @Max(value = 20, message = "Не может быть более 20 рядов")
    private Byte countRow;

    @Schema(description = "Количество мест в ряду")
    @Min(value = 5, message = "Необходимо добавить хотя бы 5 мест в ряду")
    @Max(value = 20, message = "Не может быть более 20 мест в ряду")
    private Byte countPlace;

    @Schema(description = "Тип зала")
    @NotNull(message = "Тип зала обязателен")
    private HallType hallType;



}
