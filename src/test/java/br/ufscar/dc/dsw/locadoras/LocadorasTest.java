package br.ufscar.dc.dsw.locadoras;

import br.ufscar.dc.dsw.clientes.ClientesPage;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LocadorasTest {
    LocadorasPage paginaDeLocadoras;
    ListaDeLocadorasPage paginaListaLocadoras;
    ClientesPage paginaDeClientes;
    FormularioLocadoraPage paginaDeFormularioLocadora;
    Locadora locadora;

    public LocadorasTest() {
        String cnpj = "55.789.390/0008-99";
        String nome = "Locação dos Brito";
        String senha = "12345678";
        String email = "locacaobrito@email.com";
        String cidade = "São Carlos";
        this.locadora = new Locadora(cnpj, nome, email, senha, cidade);
    }


    @AfterEach
    public void afterEach() {
        if(this.paginaDeLocadoras != null) {
            this.paginaDeLocadoras.fechar();
        } else if(this.paginaListaLocadoras != null) {
            this.paginaListaLocadoras.fechar();
        }
    }

    private void entrarComoAdmin() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioLogin("admin", "admin");
        this.paginaDeClientes = paginaDeLogin.efetuarLoginAdmin();
        this.paginaDeLocadoras = this.paginaDeClientes.acessarPaginaDeLocadoras();
        this.paginaDeLocadoras.removerLocadoraIgual(locadora);
    }


    // R2: CRUD de locadoras (requer login de administrador)
    @Test
    public void deveriaAdicionarUmaLocadoraNoSistema() {
        this.entrarComoAdmin();
        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormulario();
        this.paginaDeLocadoras = paginaDeFormularioLocadora.cadastraLocadora(locadora);

        assertTrue(this.paginaDeLocadoras.isLocadoraListada(locadora));
    }

    @Test
    public void deveriaAtualizarUmaLocadoraNoSistema() {
        this.entrarComoAdmin();

        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormulario();
        this.paginaDeLocadoras = paginaDeFormularioLocadora.cadastraLocadora(locadora);

        assertTrue(this.paginaDeLocadoras.isLocadoraListada(locadora));

        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormularioDeAtualizacaoDaLocadora(locadora);

        String novoEmail = locadora.getEmail() + ".br";
        String novaCidade = "Maceió";
        String novaSenha = "12345";

        this.paginaDeLocadoras = paginaDeFormularioLocadora.atualizarLocadora(novoEmail, novaSenha, novaCidade);

        this.locadora.setEmail(novoEmail);
        this.locadora.setCidade(novaCidade);
        this.locadora.setSenha(novaSenha);

        assertTrue(this.paginaDeLocadoras.isLocadoraListada(locadora));
    }

    @Test
    public void deveriaRemoverUmaLocadoraNoSistema() {
        this.entrarComoAdmin();

        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormulario();
        this.paginaDeLocadoras = paginaDeFormularioLocadora.cadastraLocadora(locadora);

        this.paginaDeLocadoras.deletarLocadoraDaLista(locadora);

        assertFalse(this.paginaDeLocadoras.isLocadoraListada(locadora));
    }

    // R3: Listagem de todas as locadoras em uma única página (não requer login)
    @Test
    public void deveriaListarAsLocadorasSemLogin() {
        this.entrarComoAdmin();
        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormulario();
        this.paginaDeLocadoras = paginaDeFormularioLocadora.cadastraLocadora(locadora);

        assertTrue(this.paginaDeLocadoras.isLocadoraListada(locadora));

        LoginPage paginaDeLogin = this.paginaDeLocadoras.fazerLogout();

        this.paginaListaLocadoras = paginaDeLogin.navegarParaListaDeLocadoras();

        assertTrue(this.paginaListaLocadoras.isLocadoraListada(locadora));
    }

    // R4: Listagem de todas as locadoras por cidade (não requer login)
    @Test
    public void deveriaListarAsLocadorasPorCidadeSemLogin() {
        this.entrarComoAdmin();
        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormulario();
        this.paginaDeLocadoras = paginaDeFormularioLocadora.cadastraLocadora(locadora);

        assertTrue(this.paginaDeLocadoras.isLocadoraListada(locadora));

        LoginPage paginaDeLogin = this.paginaDeLocadoras.fazerLogout();

        this.paginaListaLocadoras = paginaDeLogin.navegarParaListaDeLocadoras();

        assertTrue(this.paginaListaLocadoras.isLocadoraListada(locadora));

        this.paginaListaLocadoras.adicionarFiltroDeCidade("São Carlos");
        assertTrue(this.paginaListaLocadoras.isLocadoraListada(locadora));

        this.paginaListaLocadoras.adicionarFiltroDeCidade("Brasília");
        assertFalse(this.paginaListaLocadoras.isLocadoraListada(locadora));
    }
}
