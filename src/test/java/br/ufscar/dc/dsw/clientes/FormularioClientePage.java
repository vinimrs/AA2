package br.ufscar.dc.dsw.clientes;

import br.ufscar.dc.dsw.PageObject;
import br.ufscar.dc.dsw.domain.Cliente;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;

// page_url = http://localhost:8080/clientes/cadastro
public class FormularioClientePage extends PageObject {

    public FormularioClientePage(WebDriver driver) {
        super(driver);
    }


    public ClientesPage cadastrarCliente(Cliente cliente) {
//        browser.findElement(By.id("cpf")).sendKeys(cliente.getCpf());
//        browser.findElement(By.id("nome")).sendKeys(cliente.getNome());
//        browser.findElement(By.id("email")).sendKeys(cliente.getEmail());
//        browser.findElement(By.id("senha")).sendKeys(cliente.getSenha());
//        browser.findElement(By.id("telefone")).sendKeys(cliente.getTelefone());
//        browser.findElement(By.id("sexo")).sendKeys(cliente.getSexo());
//        browser.findElement(By.id("dataNascimento"))
//                .sendKeys(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        browser.findElement(By.id("submit-button")).click();

        return new ClientesPage(browser);
    }

    public ClientesPage atualizarCliente(String novoEmail, String novoTelefone, String novaSenha) {
        browser.findElement(By.id("telefone")).clear();
        browser.findElement(By.id("email")).clear();
        browser.findElement(By.id("senha")).clear();

        browser.findElement(By.id("telefone")).sendKeys(novoTelefone);
        browser.findElement(By.id("email")).sendKeys(novoEmail);
        browser.findElement(By.id("senha")).sendKeys(novaSenha);

        browser.findElement(By.id("submit-button")).click();

        return new ClientesPage(browser);
    }
}