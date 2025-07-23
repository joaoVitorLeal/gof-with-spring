package io.github.joaoVitorLeal.gof_with_spring.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Os atributos desse modelo foram gerados automaticamente pelo site
 * jsonschema2pojo.org. Para isso, usamos o JSON de retorno da API do ViaCEP.
 *
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 */

@Entity
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    @Column(length = 9)
    private String cep;

    @JsonProperty("logradouro") // mapear os nomes do JSON para os campos em inglês
    private String street;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("unidade")
    private String unit;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("localidade")
    private String city;

    @JsonProperty("uf")
    private String stateCode;

    @JsonProperty("estado")
    private String state;

    @JsonProperty("regiao")
    private String region;

    @JsonProperty("ibge")
    private String ibgeCode;

    @JsonProperty("gia")
    private String giaCode;

    private String ddd;

    @JsonProperty("siafi")
    private String siafiCode;

    public Address() { }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getGiaCode() {
        return giaCode;
    }

    public void setGiaCode(String giaCode) {
        this.giaCode = giaCode;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(String ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSiafiCode() {
        return siafiCode;
    }

    public void setSiafiCode(String siafiCode) {
        this.siafiCode = siafiCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(cep, address.cep) && Objects.equals(city, address.city) && Objects.equals(stateCode, address.stateCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, city, stateCode);
    }
}
