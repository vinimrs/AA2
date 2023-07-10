package br.ufscar.dc.dsw.locacoes;

import br.ufscar.dc.dsw.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

// page_url = http://localhost:8080/app/locacoes
public class LocacoesPage extends PageObject {

    private static final String URL_CADASTRO_LOCACAO = BASE_URL + "locacoes/cadastro/";
    private static final String URL_LOCACOES = BASE_URL + "locacoes/";
    public LocacoesPage(WebDriver browser) {
        super(browser);
//        browser.navigate().to(URL_LOCACOES);
    }


    public CadastroLocacaoPage carregarFormulario() {
        browser.navigate().to(URL_CADASTRO_LOCACAO);
        return new CadastroLocacaoPage(browser);
    }

    public boolean isLocacaoCadastrada(String data, String hora) {
        WebElement ultimaLinha = this.browser.findElement(By.cssSelector("#tabela-locacoes tbody tr:last-child"));
        WebElement colunaData = ultimaLinha.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaHora = ultimaLinha.findElement(By.cssSelector("td:nth-child(2)"));
//        WebElement colunaLocadora = ultimaLinha.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaData.getText().equals(data)
                && colunaHora.getText().equals(hora);
    }
}