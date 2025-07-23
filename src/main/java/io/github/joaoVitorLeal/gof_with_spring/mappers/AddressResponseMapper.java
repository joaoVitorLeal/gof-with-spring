package io.github.joaoVitorLeal.gof_with_spring.mappers;

import io.github.joaoVitorLeal.gof_with_spring.dtos.AddressResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.domain.model.Address;

// Singleton
public class AddressResponseMapper {

    private static final AddressResponseMapper instance = new AddressResponseMapper();

    private AddressResponseMapper() { }

    public static AddressResponseMapper getInstance() {
        return instance;
    }

    public AddressResponseDTO toDTO(Address address) {
        if (address == null) throw new IllegalArgumentException("The address must not be null.");

        return new AddressResponseDTO(
                address.getCep(),
                address.getStateCode(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getDdd()
        );
    }
}
