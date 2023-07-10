package br.ufscar.dc.dsw.locadoras;

import br.ufscar.dc.dsw.PageObject;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Locadora;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;

// page_url = http://localhost:8080/locadoras/cadastro
public class FormularioLocadoraPage extends PageObject {

    public FormularioLocadoraPage(WebDriver driver) {
        super(driver);
    }


    public LocadorasPage cadastraLocadora(Locadora locadora) {
        browser.findElement(By.id("cnpj")).sendKeys(locadora.getCnpj());
        browser.findElement(By.id("nome")).sendKeys(locadora.getNome());
        browser.findElement(By.id("email")).sendKeys(locadora.getEmail());
        browser.findElement(By.id("senha")).sendKeys(locadora.getSenha());
        browser.findElement(By.id("cidade")).sendKeys(locadora.getCidade());

        browser.findElement(By.id("submit-button")).click();

        return new LocadorasPage(browser);
    }

    public LocadorasPage atualizarLocadora(String novoEmail, String novaSenha, String novaCidade) {
        browser.findElement(By.id("cidade")).clear();
        browser.findElement(By.id("email")).clear();
        browser.findElement(By.id("senha")).clear();

        browser.findElement(By.id("cidade")).sendKeys(novaCidade);
        browser.findElement(By.id("email")).sendKeys(novoEmail);
        browser.findElement(By.id("senha")).sendKeys(novaSenha);

        browser.findElement(By.id("submit-button")).click();

        return new LocadorasPage(browser);
    }

}