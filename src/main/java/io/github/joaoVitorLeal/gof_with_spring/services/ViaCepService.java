package io.github.joaoVitorLeal.gof_with_spring.services;

import io.github.joaoVitorLeal.gof_with_spring.models.Address;

public interface ViaCepService {

    Address findAddress(String cep);
}
