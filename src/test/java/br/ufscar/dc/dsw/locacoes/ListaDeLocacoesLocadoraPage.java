package br.ufscar.dc.dsw.locacoes;

import br.ufscar.dc.dsw.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// page_url = http://localhost:8080/app/locacoes/lista
public class ListaDeLocacoesLocadoraPage extends PageObject {

    private static final String URL_CADASTRO_LOCACAO = BASE_URL + "locacoes/cadastro/";
    private static final String URL_LOCACOES = BASE_URL + "locacoes/";
    public ListaDeLocacoesLocadoraPage(WebDriver browser) {
        super(browser);
    }


    public CadastroLocacaoPage carregarFormulario() {
        browser.navigate().to(URL_CADASTRO_LOCACAO);
        return new CadastroLocacaoPage(browser);
    }

    public boolean isLocacaoCadastrada(String data, String hora) {
        List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-locacoes tr:not(:first-child)"));

        for (WebElement l: linhas) {
            String dataLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String horaLinha = l.findElement(By.cssSelector("td:nth-child(3)")).getText();
//            LocalDate dataLinhaFormatada = LocalDate.parse(dataLinha);
            LocalTime horaLinhaFormatada = LocalTime.parse(horaLinha);

            String dataFormatada = LocalDate.parse(data, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime horaFormatada = LocalTime.parse(hora, DateTimeFormatter.ofPattern("hmma"));

            if(dataLinha.equals(dataFormatada) && horaFormatada.equals(horaLinhaFormatada)) {
                return true;
            }
        }

        return false;
    }
}