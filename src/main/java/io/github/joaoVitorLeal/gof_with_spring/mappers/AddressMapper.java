package io.github.joaoVitorLeal.gof_with_spring.mappers;

import io.github.joaoVitorLeal.gof_with_spring.dtos.ViaCepResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(ViaCepResponseDTO dto) {
        if (dto == null) throw new IllegalArgumentException("The addressDTO must not be null.");

        Address address = new Address();
        address.setCep(dto.cep());
        address.setStreet(dto.logradouro());
        address.setComplement(dto.complemento());
        address.setUnit(dto.unidade());
        address.setNeighborhood(dto.bairro());
        address.setCity(dto.localidade());
        address.setStateCode(dto.uf());
        address.setState(dto.estado());
        address.setRegion(dto.regiao());
        address.setIbgeCode(dto.ibge());
        address.setGiaCode(dto.gia());
        address.setDdd(dto.ddd());
        address.setSiafiCode(dto.siafi());
        return address;
    }

    public ViaCepResponseDTO toDTO(Address address) {
        if (address == null) throw new IllegalArgumentException("The Address must not be null.");

        return new ViaCepResponseDTO(
                address.getCep(),
                address.getStreet(),
                address.getComplement(),
                address.getUnit(),
                address.getNeighborhood(),
                address.getCity(),
                address.getStateCode(),
                address.getState(),
                address.getRegion(),
                address.getIbgeCode(),
                address.getGiaCode(),
                address.getDdd(),
                address.getSiafiCode(),
                false // erro, que não existe na entidade, fica null ou false aqui
        );
    }
}
