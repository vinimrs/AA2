package br.ufscar.dc.dsw.domain;

import br.ufscar.dc.dsw.validation.UniqueCNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario {

//    @UniqueCPF(message = "{Unique.editora.CNPJ}")
    @NotBlank
    @Size(min = 15, max = 15)
    @Column(unique = true)
    private String cpf;

    @NotBlank
    @Size(min = 15, max = 15)
    private String phoneNumber;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Sexo sex;

    @NotBlank
    @Column(columnDefinition = "Date")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "client")
    private List<Locacao> locacoes;

    public Cliente() {
        this.role = "CLIENTE";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Sexo getSex() {
        return sex;
    }

    public void setSex(Sexo sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
