package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Admin")
public class Admin extends Usuario {
    @Override
    public String getRole() {
        return "ADMIN";
    }
}
