package io.github.joaoVitorLeal.gof_with_spring.repositories;

import io.github.joaoVitorLeal.gof_with_spring.models.Address;
import io.github.joaoVitorLeal.gof_with_spring.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    long countByAddress(Address address);
}
