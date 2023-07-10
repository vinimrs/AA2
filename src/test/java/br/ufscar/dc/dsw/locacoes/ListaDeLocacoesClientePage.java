package br.ufscar.dc.dsw.locacoes;

import br.ufscar.dc.dsw.PageObject;
import br.ufscar.dc.dsw.domain.Locadora;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// page_url = http://localhost:8080/app/locacoes/lista
public class ListaDeLocacoesClientePage extends PageObject {

    private static final String URL_CADASTRO_LOCACAO = BASE_URL + "locacoes/cadastro/";
    private static final String URL_LOCACOES = BASE_URL + "locacoes/";
    public ListaDeLocacoesClientePage(WebDriver browser) {
        super(browser);
    }


    public CadastroLocacaoPage carregarFormulario() {
        WebElement botaoCriarLocacao = browser.findElement(By.id("create-locacao-link"));
        botaoCriarLocacao.click();

        return new CadastroLocacaoPage(browser);
    }

    public boolean isLocacaoCadastrada(String data, String hora) {
        List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-locacoes tr:not(:first-child)"));

        for (WebElement l: linhas) {
            String dataLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String horaLinha = l.findElement(By.cssSelector("td:nth-child(3)")).getText();
//            LocalDate dataLinhaFormatada = LocalDate.parse(dataLinha);
            LocalTime horaLinhaFormatada = LocalTime.parse(horaLinha);

            System.out.println(dataLinha + horaLinha);

            String dataFormatada = LocalDate.parse(data, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime horaFormatada = LocalTime.parse(hora, DateTimeFormatter.ofPattern("hmma"));

            System.out.println(dataFormatada + horaFormatada);

            if(dataLinha.equals(dataFormatada) && horaFormatada.equals(horaLinhaFormatada)) {
                return true;
            }
        }

        return false;
    }

    public void fazerLogout() {
        browser.findElement(By.id("logout-button")).click();
    }
}