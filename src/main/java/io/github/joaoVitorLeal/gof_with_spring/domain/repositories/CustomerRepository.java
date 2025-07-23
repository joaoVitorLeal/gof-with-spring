package io.github.joaoVitorLeal.gof_with_spring.domain.repositories;

import io.github.joaoVitorLeal.gof_with_spring.domain.model.Address;
import io.github.joaoVitorLeal.gof_with_spring.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    long countByAddress(Address address);
}
