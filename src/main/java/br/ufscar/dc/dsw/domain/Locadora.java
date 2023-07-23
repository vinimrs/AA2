package br.ufscar.dc.dsw.domain;

import br.ufscar.dc.dsw.validation.UniqueCNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Locadora")
public class Locadora extends Usuario {

    @UniqueCNPJ(message = "{Unique.locadora.CNPJ}")
    @NotBlank
    @Size(min = 18, max = 18, message = "{Size.locadora.CNPJ}")
    @Column(unique = true)
    private String cnpj;

    @NotBlank
    @Column(length = 50)
    private String city;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "rentalCompany")
    private List<Locacao> locacoes;

    public Locadora() {
        this.role = "ROLE_LOCADORA";
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

    public List<Locacao> getLocacoes() {
        return locacoes;
    }
}
