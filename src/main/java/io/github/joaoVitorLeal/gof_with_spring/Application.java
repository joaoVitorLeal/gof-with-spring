package io.github.joaoVitorLeal.gof_with_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Habilitar o Feign
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
