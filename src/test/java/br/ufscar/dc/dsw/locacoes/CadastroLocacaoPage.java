package br.ufscar.dc.dsw.locacoes;

import br.ufscar.dc.dsw.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

// page_url = http://localhost:8080/app/locacoes/cadastro
public class CadastroLocacaoPage extends PageObject {
    private static final String URL_CADASTRO_LOCACAO = BASE_URL + "locacoes/cadastro/";

    public CadastroLocacaoPage(WebDriver browser) {
        super(browser);
    }

    public ListaDeLocacoesClientePage cadastrarLocacao(String data, String hora) {
        WebElement ultimaLinha = this.browser.findElement(By.cssSelector("#tabela-locadoras tbody tr:last-child"));
        WebElement input = ultimaLinha.findElement(By.cssSelector("td:nth-child(1)"));
        input.click();

        browser.findElement(By.id("data")).sendKeys(data);
        browser.findElement(By.id("hora")).sendKeys(hora);

        browser.findElement(By.id("submit-button")).click();

        // ou retornar locadora
        return new ListaDeLocacoesClientePage(browser);
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_CADASTRO_LOCACAO);
    }

    public boolean isMensagensDeValidacoesVisiveis() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("não deve estar em branco")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");

    }
}