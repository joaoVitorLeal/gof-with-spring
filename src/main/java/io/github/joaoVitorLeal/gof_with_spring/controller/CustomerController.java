package io.github.joaoVitorLeal.gof_with_spring.controller;

import io.github.joaoVitorLeal.gof_with_spring.dtos.CustomerRequestDTO;
import io.github.joaoVitorLeal.gof_with_spring.dtos.CustomerResponseDTO;
import io.github.joaoVitorLeal.gof_with_spring.domain.model.Customer;
import io.github.joaoVitorLeal.gof_with_spring.domain.services.CustomerService;
import io.github.joaoVitorLeal.gof_with_spring.shared.UriLocationBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados Postgres e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 */
@RestController
@RequestMapping("/v1/clients")
@Tag(name = "Customers")
public class CustomerController implements UriLocationBuilder {

    // @Autowired removido - injeção de dependências via construtor
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CustomerRequestDTO dto) {
        Customer customer = service.create(dto);
        URI location = buildLocationUri(customer.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<CustomerResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK.value()).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(UUID.fromString(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody @Valid CustomerRequestDTO dto) {
        service.update(UUID.fromString(id), dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        service.deleteById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
