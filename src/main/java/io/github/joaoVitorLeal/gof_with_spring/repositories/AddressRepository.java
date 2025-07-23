package io.github.joaoVitorLeal.gof_with_spring.repositories;

import io.github.joaoVitorLeal.gof_with_spring.models.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends CrudRepository<Address, UUID> {

    Optional<Address> findByCep(String cep);

    void deleteByCep(String cep);
}
