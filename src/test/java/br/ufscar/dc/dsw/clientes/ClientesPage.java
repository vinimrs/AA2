package br.ufscar.dc.dsw.clientes;

import br.ufscar.dc.dsw.PageObject;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.locadoras.LocadorasPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// page_url = http://localhost:8080/clientes/
public class ClientesPage extends PageObject {
    private static final String URL_CADASTRO_CLIENTES = BASE_URL + "clientes/cadastro/";
    private static final String URL_CLIENTES = BASE_URL + "clientes/";
    public ClientesPage(WebDriver browser) {
        super(browser);
//        browser.navigate().to(URL_CLIENTES);
    }

    public FormularioClientePage carregarFormulario() {
        browser.findElement(By.id("create-client-button")).click();
//        browser.navigate().to(URL_CADASTRO_CLIENTES);
        return new FormularioClientePage(browser);
    }

    public boolean isClienteListado(String email) {
      List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-clientes tr:not(:first-child)"));

      for (WebElement l: linhas) {
        String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();

        if(emailLinha.equals(email)) {
          return true;
        }
      }

      return false;
    }
  public FormularioClientePage carregarFormularioDeAtualizacaoDoCliente(Cliente cliente) {
    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-clientes tr:not(:first-child)"));

    for (WebElement l: linhas) {
      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String cpfLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();

      if(emailLinha.equals(cliente.getEmail()) || cpfLinha.equals(cliente.getCpf())) {
        WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
        WebElement acaoDeAtualizar = colunaActions.findElement(By.cssSelector("a:nth-child(1)"));
        acaoDeAtualizar.click();

        break;
      }
    }

    return new FormularioClientePage(browser);
  }

  public Cliente deletarClienteDaLista(Cliente cliente) {
    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-clientes tr:not(:first-child)"));

    for (WebElement l: linhas) {
      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String cpfLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();

      if(emailLinha.equals(cliente.getEmail()) || cpfLinha.equals(cliente.getCpf())) {
        WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
        WebElement acaoDeDeletar = colunaActions.findElement(By.cssSelector("a:nth-child(2)"));
        acaoDeDeletar.click();

        browser.switchTo().alert().accept();

        break;
      }
    }

    return cliente;
  }

  public boolean isPaginaDeClientes() {
      return browser.getCurrentUrl().equals(URL_CLIENTES);
  }

  public void removerClienteComEmailOuCpfIgual(String email, String cpf) {
    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-clientes tr:not(:first-child)"));

    for (WebElement l: linhas) {
      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String cpfLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();

      if(emailLinha.equals(email) || cpfLinha.equals(cpf)) {
        WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
        WebElement acaoDeDeletar = colunaActions.findElement(By.cssSelector("a:nth-child(2)"));
        acaoDeDeletar.click();

        browser.switchTo().alert().accept();

        break;
      }
    }
  }

  public LocadorasPage acessarPaginaDeLocadoras() {
      WebElement linkParaLocadoras = this.browser.findElement(By.id("link-locadoras"));
      linkParaLocadoras.click();

      return new LocadorasPage(browser);
  }
}