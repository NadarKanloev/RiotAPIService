package com.nadarkanloev.riotapiservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class RiotUserDTO {
    @Schema(description = "Имя игрока", example = "VeventumT")
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String summonerName;

    @Schema(description = "Регион", example = "ru - Россия, br1 - Бразилия, eun1 - Северный Европейский регион, euw1 - Западная Европа, jp1 - Япония и т.д")
    @NotBlank(message = "Поле региона не должно быть пустым")
    private String region;
}
