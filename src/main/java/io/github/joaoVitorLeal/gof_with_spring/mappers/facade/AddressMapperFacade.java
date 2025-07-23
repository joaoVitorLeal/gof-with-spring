package io.github.joaoVitorLeal.gof_with_spring.mappers.facade;

import io.github.joaoVitorLeal.gof_with_spring.dtos.AddressResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.dtos.ViaCepResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.mappers.AddressMapper;
import io.github.joaoVitorLeal.gof_with_spring.mappers.AddressResponseMapper;
import io.github.joaoVitorLeal.gof_with_spring.models.Address;

/**
 * Classe fachada (FACADE) que centraliza as conversões entre entidade Address,
 * dados provenientes de ViaCep API e seus respectivos DTOs.
 *
 * Utiliza os mapeadores:
 * @see io.github.joaoVitorLeal.gof_with_spring.mappers.AddressMapper
 * @see io.github.joaoVitorLeal.gof_with_spring.mappers.AddressResponseMapper
 */
public class AddressMapperFacade {

    private static final AddressMapper addressMapper = new AddressMapper();
    private static final AddressResponseMapper responseMapper = AddressResponseMapper.getInstance();

    private AddressMapperFacade() {
        // Classe utilitária não deve ser instanciada
    }

    public static Address toEntity(ViaCepResponseDTO dto) {
        return addressMapper.toEntity(dto);
    }

    public static ViaCepResponseDTO toViaCepDTO(Address address) {
        return addressMapper.toDTO(address);
    }

    public static AddressResponseDTO toAddressResponseDTO(Address address) {
        return responseMapper.toDTO(address);
    }
}
