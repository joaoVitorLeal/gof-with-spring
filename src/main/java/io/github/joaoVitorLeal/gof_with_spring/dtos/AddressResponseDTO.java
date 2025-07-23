package io.github.joaoVitorLeal.gof_with_spring.dtos;

public record AddressResponseDTO(
        String cep,
        String stateCode,
        String city,
        String neighborhood,
        String street,
        String ddd
    ) {
}
