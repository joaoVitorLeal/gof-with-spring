package io.github.joaoVitorLeal.gof_with_spring.services.impl;

import io.github.joaoVitorLeal.gof_with_spring.client.ViaCepClient;
import io.github.joaoVitorLeal.gof_with_spring.models.Address;
import io.github.joaoVitorLeal.gof_with_spring.services.ViaCepService;
import org.springframework.stereotype.Service;

@Service
public abstract class ViaCepServiceImpl implements ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepServiceImpl(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @Override
    public Address findAddress(String cep) {
        return viaCepClient.findAddressByCep(cep);
    }
}
