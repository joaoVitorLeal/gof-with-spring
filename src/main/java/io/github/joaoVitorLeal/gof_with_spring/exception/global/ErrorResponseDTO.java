package io.github.joaoVitorLeal.gof_with_spring.exception.global;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

public record ErrorResponseDTO(
        @NotNull
        Instant timestamp,

        @NotNull
        int statusCode,

        List<String> errors,

        @NotBlank
        String path
) {

    public ErrorResponseDTO(int statusCode, List<String> errors, String path) {
        this(Instant.now(),statusCode, errors, path);
    }
}
