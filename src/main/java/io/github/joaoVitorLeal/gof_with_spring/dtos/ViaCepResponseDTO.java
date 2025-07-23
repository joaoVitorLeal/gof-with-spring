package io.github.joaoVitorLeal.gof_with_spring.dtos;

public record ViaCepResponseDTO(
        String cep,
        String logradouro,
        String complemento,
        String unidade,
        String bairro,
        String localidade,
        String uf,
        String estado,
        String regiao,
        String ibge,
        String gia,
        String ddd,
        String siafi,
        Boolean erro // pode vir em caso de CEP válido, mas inexistente
    ) { }
