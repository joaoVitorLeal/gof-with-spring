package io.github.joaoVitorLeal.gof_with_spring.client;

import io.github.joaoVitorLeal.gof_with_spring.domain.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Adapter para consumo da API ViaCEP usando OpenFeign.
 *
 * Esta interface adapta a API externa ViaCEP para um método Java
 * que retorna um {@link Address}, permitindo que a aplicação utilize
 * essa funcionalidade de forma transparente e desacoplada.
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 */
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws") // url base do client do viaCep
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    Address findAddressByCep(@PathVariable("cep") String cep);
}
