package io.github.joaoVitorLeal.gof_with_spring.domain.services.impl;

import io.github.joaoVitorLeal.gof_with_spring.dtos.CustomerRequestDTO;
import io.github.joaoVitorLeal.gof_with_spring.dtos.CustomerResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.domain.exceptions.CustomerNotFoundException;
import io.github.joaoVitorLeal.gof_with_spring.domain.exceptions.InvalidFieldsCustomerException;
import io.github.joaoVitorLeal.gof_with_spring.mappers.CustomerMapper;
import io.github.joaoVitorLeal.gof_with_spring.domain.model.Address;
import io.github.joaoVitorLeal.gof_with_spring.domain.model.Customer;
import io.github.joaoVitorLeal.gof_with_spring.domain.repositories.AddressRepository;
import io.github.joaoVitorLeal.gof_with_spring.domain.repositories.CustomerRepository;
import io.github.joaoVitorLeal.gof_with_spring.domain.services.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final AddressRepository addressRepository;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper, AddressRepository addressRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.addressRepository = addressRepository;
    }

    @Override
    public Iterable<CustomerResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new CustomerNotFoundException("Not found customer with ID: " + id));
    }

    @Override
    @Transactional
    public Customer create(CustomerRequestDTO dto) {
        if (dto == null) {
            throw new InvalidFieldsCustomerException("Customer data must not be null.");
        }

        Customer customer = mapper.toEntity(dto);

        // Salva o Address e ATUALIZA a referência no Customer com address já com estado 'managed'
        Address savedAddress = addressRepository.save(customer.getAddress());
        customer.setAddress(savedAddress); // Atualiza para a instância gerenciada

        return repository.save(customer);
    }

    @Override
    @Transactional
    public void update(UUID id, CustomerRequestDTO dto) {
        if (dto == null) {
            throw new InvalidFieldsCustomerException("Customer data must not be null.");
        }

        Customer customer = findByIdUtils(id);
        customer.setName(dto.name());

        // Processamento de endereço
        Address newAddress = mapper.toEntity(dto).getAddress();

        // Verificar se já existe um endereço com mesmo CEP na base
        addressRepository.findByCep(newAddress.getCep())
                .ifPresentOrElse(
                        // Caso 1: reutiliza endereço existente
                        customer::setAddress,
                        () -> {
                            // Caso 2: Cria novo endereço
                            Address updatedAddress = addressRepository.save(newAddress);
                            customer.setAddress(updatedAddress);
                        }
                );

        repository.save(customer);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {

        repository.findById(id)
                .ifPresentOrElse(customer -> {
                            repository.delete(customer);
                            // Deletar endereço somente se estiver em desuso
                            if (isAddressUnused(customer.getAddress())) {
                                addressRepository.deleteByCep(customer.getAddress().getCep());
                            }
                        },
                        () -> {
                            throw new CustomerNotFoundException("Not found user with ID: " + id);
                        }
                );
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        Customer existingCustomer = findByIdUtils(customer.getId());
        Address associatedAddress = existingCustomer.getAddress();

        repository.delete(existingCustomer);

        if (isAddressUnused(associatedAddress)) {
            addressRepository.delete(associatedAddress);
        }
    }

    @Deprecated(forRemoval = true, since = "1.0.0")
    @Transactional
    public void BROKEN_deleteById(UUID id) {
        repository.findById(id)
                .ifPresentOrElse( c -> {
                    repository.delete(c);
                    addressRepository.deleteByCep(c.getAddress().getCep());
                },
                        () -> {
                            throw new CustomerNotFoundException("Not found customer with ID: " + id);
                        }
                );
    }

    private Customer findByIdUtils(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Not found customer with ID: " + id));
    }

    private boolean isAddressUnused(Address address) {
        return repository.countByAddress(address) == 0;
    }
}
