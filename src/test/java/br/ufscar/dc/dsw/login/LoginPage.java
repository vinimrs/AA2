package br.ufscar.dc.dsw.login;

import br.ufscar.dc.dsw.PageObject;
import br.ufscar.dc.dsw.clientes.ClientesPage;
import br.ufscar.dc.dsw.locacoes.ListaDeLocacoesClientePage;
import br.ufscar.dc.dsw.locacoes.ListaDeLocacoesLocadoraPage;
import br.ufscar.dc.dsw.locacoes.LocacoesPage;
import br.ufscar.dc.dsw.locadoras.ListaDeLocadorasPage;
import br.ufscar.dc.dsw.locadoras.LocadorasPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

// page_url = http://localhost:8080/app/
public class LoginPage extends PageObject {

  public LoginPage() {
    super();
    this.browser.navigate().to(BASE_URL);
  }

  public boolean isPaginaDeLogin() {
    return browser.getCurrentUrl().equals(BASE_URL);
  }

  public boolean contemTexto(String texto) {
    return browser.getPageSource().contains(texto);
  }


  public void preencheFormularioLogin(String login, String password) {
    browser.findElement(By.id("login")).sendKeys(login);
    browser.findElement(By.id("password")).sendKeys(password);
  }

  public boolean isPaginaDeLoginComDadosInvalidos() {
    return browser.getPageSource().contains("Usuário não encontrado!");
  }

  public ListaDeLocacoesClientePage efetuarLoginCliente() {
    browser.findElement(By.id("input-submit")).click();
    return new ListaDeLocacoesClientePage(browser);
  }

  public ClientesPage efetuarLoginAdmin() {
    browser.findElement(By.id("input-submit")).click();
    return new ClientesPage(browser);
  }

  // verificar qual pagina a locadora vai dps do login
    public ListaDeLocacoesLocadoraPage efetuarLoginLocadora() {
      browser.findElement(By.id("input-submit")).click();
      return new ListaDeLocacoesLocadoraPage(browser);
    }

  public void navegarParaPaginaDeLocadoras() {
    this.browser.navigate().to(BASE_URL+ "locadoras/" );
  }

  public void navegarParaPaginaDeClientes() {
    this.browser.navigate().to(BASE_URL+ "clientes/" );
  }


  public ListaDeLocadorasPage navegarParaListaDeLocadoras() {
    this.browser.navigate().to(BASE_URL+ "locadoras/" );
    return new ListaDeLocadorasPage(browser);
  }
}