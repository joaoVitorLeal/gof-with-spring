package io.github.joaoVitorLeal.gof_with_spring.domain.services;

import io.github.joaoVitorLeal.gof_with_spring.dtos.CustomerRequestDTO;
import io.github.joaoVitorLeal.gof_with_spring.dtos.CustomerResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.domain.model.Customer;

import java.util.UUID;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 */
public interface CustomerService {

    Iterable<CustomerResponseDTO> findAll();

    CustomerResponseDTO findById(UUID id);

    Customer create(CustomerRequestDTO customerDto);

    void update(UUID id, CustomerRequestDTO customerDto);

    void delete(Customer customer);

    void deleteById(UUID id);
}
