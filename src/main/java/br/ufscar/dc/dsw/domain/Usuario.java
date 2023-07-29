package br.ufscar.dc.dsw.domain;

import br.ufscar.dc.dsw.validation.Name;
import br.ufscar.dc.dsw.validation.uniques.UniqueEmail;
import br.ufscar.dc.dsw.validation.uniques.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/*
InheritanceType.JOINED = Tabelas para as classes filhas e para a classe pai
                          ao ser feito as buscas será usado o ID gerado
                          automaticamente para a classe mãe,
                          fazendo a busca nas classes filhas.

                          Solucao encontrada para problemas de
                          validação de campos que não podem ser
                          nulos nas classes filhas.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Usuario")
public abstract class Usuario extends AbstractEntity<Long> {

  @UniqueUsername(message = "{Unique.user.username}")
  @NotBlank
  @Column(length = 20, unique = true)
  private String username;

  @UniqueEmail(message = "{Unique.user.email}")
  @NotBlank
  @Column(nullable = false, length = 40, unique = true)
  private String email;

  @NotBlank
  @Column(nullable = false, length = 64)
  private String password;

  @Name(message = "{Name.user.name}")
  @NotBlank
  @Column(nullable = false, length = 60)
  private String name;

  @NotBlank
  @Column(nullable = false, length = 20)
  protected String role;

  @Column(nullable = false)
  private boolean enabled;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}