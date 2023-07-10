package br.ufscar.dc.dsw.clientes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClientesTest {
    ClientesPage paginaDeClientes;
    FormularioClientePage paginaDeFormularioCliente;
    Cliente cliente;

    public ClientesTest() {
        String cpf = "111.111.000-00";
        String nome = "Joaquin das Neves";
        String email = "joaquin11111@email.com";
        String senha = "12345678";
        String telefone = "(82)99183-9092";
        String sexo = "Masculino";
        LocalDate dataNascimento = LocalDate.parse("2004-02-18");
        this.cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, dataNascimento);
    }

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioLogin("admin", "admin");
        this.paginaDeClientes = paginaDeLogin.efetuarLoginAdmin();
        this.paginaDeClientes.removerClienteComEmailOuCpfIgual(cliente.getEmail(), cliente.getCpf());

    }

    @AfterEach
    public void afterEach() {
        this.paginaDeClientes.fechar();
    }


    // R1: CRUD de clientes (requer login de administrador)
    @Test
    public void deveriaAdicionarUmClienteNoSistema() {

        this.paginaDeFormularioCliente = paginaDeClientes.carregarFormulario();
        this.paginaDeClientes = paginaDeFormularioCliente.cadastrarCliente(cliente);

        assertTrue(this.paginaDeClientes.isClienteListado(cliente.getEmail()));
    }

    @Test
    public void deveriaAtualizarUmClienteNoSistema() {

        this.paginaDeFormularioCliente = paginaDeClientes.carregarFormulario();
        this.paginaDeClientes = paginaDeFormularioCliente.cadastrarCliente(cliente);

        assertTrue(this.paginaDeClientes.isClienteListado(cliente.getEmail()));

        this.paginaDeFormularioCliente = paginaDeClientes.carregarFormularioDeAtualizacaoDoCliente(cliente);

        String novoEmail = cliente.getEmail() + ".br";
        String novoTelefone = "82988888888";
        String novaSenha = "12345";

        this.paginaDeClientes = paginaDeFormularioCliente.atualizarCliente(novoEmail, novoTelefone, novaSenha);

        assertTrue(this.paginaDeClientes.isClienteListado(novoEmail));
    }

    @Test
    public void deveriaRemoverUmClienteNoSistema() {
        this.paginaDeFormularioCliente = paginaDeClientes.carregarFormulario();
        String cpf = "111.111.000-00";
        String nome = "Joaquin das Neves";
        String email = "joaquin11111@email.com";
        String senha = "12345678";
        String telefone = "(82)99183-9092";
        String sexo = "Masculino";
        String dataNascimento = "18/02/2004";
        this.paginaDeClientes.removerClienteComEmailOuCpfIgual(email, cpf);


        this.paginaDeClientes = paginaDeFormularioCliente.cadastrarCliente(cliente);

        this.paginaDeClientes.deletarClienteDaLista(cliente);

        assertFalse(this.paginaDeClientes.isClienteListado(cliente.getEmail()));
    }
}
