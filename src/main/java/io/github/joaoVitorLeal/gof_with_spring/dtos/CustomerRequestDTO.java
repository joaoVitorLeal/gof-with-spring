package io.github.joaoVitorLeal.gof_with_spring.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequestDTO(

        @NotBlank(message = "The 'name' field must not be blank.")
        @Size(min = 3, max = 150, message = "The name must be between 3 and 150 characters long.")
        String name,

        @NotBlank(message = "The 'CEP' field must not be blank.")
        @Pattern(regexp = "\\d{8}", message = "The ZIP code (CEP) must contain exactly 8 numeric digits.")
        String cep
    ) { }

