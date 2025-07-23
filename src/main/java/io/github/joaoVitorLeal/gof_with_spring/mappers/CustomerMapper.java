package io.github.joaoVitorLeal.gof_with_spring.mappers;

import io.github.joaoVitorLeal.gof_with_spring.client.ViaCepClient;
import io.github.joaoVitorLeal.gof_with_spring.dtos.AddressResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.dtos.CustomerRequestDTO;
import io.github.joaoVitorLeal.gof_with_spring.dtos.CustomerResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.mappers.facade.AddressMapperFacade;
import io.github.joaoVitorLeal.gof_with_spring.models.Address;
import io.github.joaoVitorLeal.gof_with_spring.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private final ViaCepClient viaCepClient;


    private CustomerMapper(ViaCepClient viaCepClientService) {
        this.viaCepClient = viaCepClientService;
    }


    public Customer toEntity(CustomerRequestDTO dto) {
        Address address = viaCepClient.findAddressByCep(dto.cep());
        return new Customer(dto.name(), address);
    }

    public CustomerResponseDTO toDTO(Customer entity) {
        AddressResponseDTO addressResponseDto = AddressMapperFacade.toAddressResponseDTO(entity.getAddress());
        return new CustomerResponseDTO(entity.getName(), addressResponseDto);
    }
}
