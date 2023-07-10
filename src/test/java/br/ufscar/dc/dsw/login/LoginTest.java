package br.ufscar.dc.dsw.login;

import br.ufscar.dc.dsw.clientes.ClientesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginTest {

  private LoginPage paginaDeLogin;

  @BeforeEach
  public void beforeEach() {
    this.paginaDeLogin = new LoginPage();
  }

  @AfterEach
  public void afterEach() {
    this.paginaDeLogin.fechar();
  }

  // Testes de aceitação ou E2E
  @Test
  public void deveriaEfetuarLoginComoAdminComDadosValidos() {
    paginaDeLogin.preencheFormularioLogin("admin", "admin");
    ClientesPage paginaDeClientes = paginaDeLogin.efetuarLoginAdmin();

    assertTrue(paginaDeClientes.isPaginaDeClientes());
  }

  @Test
  public void naoDeveriaLogarComDadosInvalidos() {
    paginaDeLogin.preencheFormularioLogin("invalido", "pass");
    paginaDeLogin.efetuarLoginAdmin();

    assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
  }

  // teste de autenticacao
  @Test
  public void naoDeveriaConseguirAcessarPaginaRestritaSemEstarLogado() {
    paginaDeLogin.navegarParaPaginaDeClientes();

    assertTrue(paginaDeLogin.isPaginaDeLogin());

    paginaDeLogin.navegarParaPaginaDeClientes();

    assertTrue(paginaDeLogin.isPaginaDeLogin());
  }
}
