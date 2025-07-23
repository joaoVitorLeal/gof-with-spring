package io.github.joaoVitorLeal.gof_with_spring.dtos;

public record CustomerResponseDTO(
        String name,
        AddressResponseDTO address
    ) { }
