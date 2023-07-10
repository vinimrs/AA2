package br.ufscar.dc.dsw.domain;

import br.ufscar.dc.dsw.validation.UniqueCNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "Locadora")
public class Locadora extends Usuario {

    @UniqueCNPJ(message = "{Unique.locadora.CNPJ}")
    @NotBlank
    @Size(min = 15, max = 15, message = "{Size.locadora.CNPJ}")
    @Column(unique = true)
    private String cnpj;

    @NotBlank
    @Column(length = 50)
    private String city;

    public Locadora() {
        this.role = "LOCADORA";
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
