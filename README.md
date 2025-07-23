# GOF com Spring Boot

Este projeto tem como objetivo demonstrar a aplicação de **Design Patterns** do catálogo GoF (Gang of Four) em conjunto com as facilidades do **Spring Boot**.

---

## 📋 Visão Geral

• **Integração com API pública ViaCep** para busca de endereços via CEP.

• **Documentação funcional via OpenAPI (Swagger)** disponível em `http://localhost:8080/swagger-ui.html`.

• **Padrões de projeto explícitos** nas classes de negócio:

* **Facade**: `AddressMapperFacade.java`
* **Singleton**: `AddressResponseMapper.java`

• **Padrões de projeto implícitos** fornecidos pelo Spring Boot e OpenFeign:

* **Singleton** (GoF): todos os *beans* do Spring são **singleton** por padrão.
* **Proxy** (GoF): gerado pelo Spring para transações e pelo OpenFeign para chamadas HTTP remotas da integração com a API do ViaCep.
* **Strategy** (GoF): implementado pelo Spring Data JPA para troca de estratégias de acesso a dados.
* **Template Method** o Spring Data JPA define o fluxo de execução de operações de persistência, permitindo que métodos concretos sejam personalizados sem alterar a estrutura principal da lógica.

---

## 🛠 Tecnologias Utilizadas

* Java 17
* Spring Boot 3.5.3
* Spring Web (REST)
* Spring Data JPA
* Spring Boot Starter Validation
* Spring Cloud OpenFeign (Client HTTP via interface)
* SpringDoc OpenAPI Starter WebMvc UI
* PostgreSQL (runtime)

---

## ⚙️ Configuração de Banco de Dados

As configurações de conexão com o PostgreSQL estão em `src/main/resources/application.yml`:

```yaml
spring:
  application:
    name: gof-with-spring
    output:
      ansi:
        enabled: ALWAYS # Cores no console para logs
  datasource:
    url: ${DATASOURCE_URL:jdbc:postgresql://localhost:5433/design_patterns_dio_class}
    username: ${DATASOURCE_USERNAME}
    password: ${DATASOURCE_PASSWORD}
    driverClassName: org.postgresql.Driver

  jpa:
    sql-show: true
    hibernate:
      ddl-auto: create-drop # Cria e remove o schema a cada execução
    properties:
      hibernate.format_sql: true
```

**Observação:** As variáveis de ambiente `DATASOURCE_URL`, `DATASOURCE_USERNAME` e `DATASOURCE_PASSWORD` podem ser definidas no seu ambiente ou arquivo `.env`.

---

## 🌐 Integração com ViaCep

Este projeto consome a API do ViaCep para buscar informações de endereço a partir do CEP informado. A interface de cliente está em:

```
src/main/java/io/github/joaovitorleal/gof_with_spring/client/ViaCepClient.java
```

O DTO de resposta fica em:

```
src/main/java/io/github/joaovitorleal/gof_with_spring/dtos/ViaCepResponseDTO.java
```

---

## 🚀 Endpoints REST

| Método | Caminho           | Descrição                 |
| ------ | ----------------- | ------------------------- |
| POST   | `/customers`      | Cria um novo cliente      |
| GET    | `/customers`      | Lista todos os clientes   |
| GET    | `/customers/{id}` | Busca cliente por ID      |
| PUT    | `/customers/{id}` | Atualiza dados do cliente |
| DELETE | `/customers/{id}` | Remove cliente por ID     |

> **Exemplo de uso:**
>
> ```bash
> curl -X POST http://localhost:8080/customers \
>      -H 'Content-Type: application/json' \
>      -d '{"name":"João","cep":"01001000"}'
> ```

---

## 📑 Documentação via OpenAPI (Swagger)

Acesse a interface interativa do Swagger em:

```
http://localhost:8080/swagger-ui.html
```

Ela exibe todos os endpoints, modelos de dados (DTOs) e permite testar requisições diretamente.

---

## 🧩 Padrões de Projeto

* **Facade (GoF)**: `AddressMapperFacade` centraliza mapeamentos complexos de endereço.
* **Singleton (GoF)**: `AddressResponseMapper` garante uma única instância do mapper.
* **Service Layer**: separação clara entre lógica de negócio (`CustomerService`) e controllers.
* **Repository (GoF Bridge/DAO)**: uso de `CustomerRepository` e `AddressRepository` com Spring Data JPA.
* **Implicitamente**:


• **Padrões de projeto implícitos** fornecidos pelo Spring Boot e OpenFeign:

* **Singleton** (GoF): todos os *beans* do Spring são **singleton** por padrão.
* **Proxy** (GoF): gerado pelo Spring para transações e pelo OpenFeign para chamadas HTTP remotas da integração com a API do ViaCep.
* **Strategy** (GoF): implementado pelo Spring Data JPA para troca de estratégias de acesso a dados.
* **Template Method** (GoF): o Spring Data JPA define o fluxo de execução de operações de persistência, permitindo que métodos concretos sejam personalizados sem alterar a estrutura principal da lógica.

---

## 📥 Como Executar

1. Clone este repositório
2. Configure as variáveis de ambiente:

   ```bash
   export DATASOURCE_URL=jdbc:postgresql://localhost:porta/nome_do_db
   export DATASOURCE_USERNAME=seu_usuario
   export DATASOURCE_PASSWORD=sua_senha
   ```
3. Execute via Maven:

   ```bash
   ./mvnw spring-boot:run
   ```
    Ou diretamente pela IDE.
---

## 🤝 Contribuições

Contribuições são bem-vindas! Abra uma *issue* ou *pull request* explicando a melhoria.

---

## ⚖️ Licença

MIT License.
