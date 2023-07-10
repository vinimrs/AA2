package br.ufscar.dc.dsw.locacoes;

import br.ufscar.dc.dsw.clientes.ClientesPage;
import br.ufscar.dc.dsw.login.LoginPage;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LocacoesTest {
  private LocacoesPage paginaDeLocacoes;
  private ListaDeLocacoesClientePage paginaListaLocacoesCliente;
  private ListaDeLocacoesLocadoraPage paginaListaLocacoesLocadora;
  private CadastroLocacaoPage paginaDeCadastro;

  @AfterEach
  public void afterEach() {
    if (this.paginaListaLocacoesCliente != null) {
      this.paginaListaLocacoesCliente.fechar();
    } else if (this.paginaListaLocacoesLocadora != null) {
      this.paginaListaLocacoesLocadora.fechar();
    }
  }

  private void entrarComoCliente() {
    LoginPage paginaDeLogin = new LoginPage();
    paginaDeLogin.preencheFormularioLogin("viniciusromualdo@estudante.ufscar.br", "12345");
    this.paginaListaLocacoesCliente = paginaDeLogin.efetuarLoginCliente();
  }

  private void entrarComoLocadora() {
    LoginPage paginaDeLogin = new LoginPage();
    paginaDeLogin.preencheFormularioLogin("viniromualdo082@gmail.com", "12345");
    this.paginaListaLocacoesLocadora = paginaDeLogin.efetuarLoginLocadora();
  }

  // R6: Listagem de todas as locacoes de um cliente
  @Test
  public void deveriaListarLocacoesDeUmCliente() {
    this.entrarComoCliente();

    this.paginaDeCadastro = paginaListaLocacoesCliente.carregarFormulario();

    String data = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    // escolher uma hora livre depois da atual
    String hora = "700PM";

    this.paginaListaLocacoesCliente = paginaDeCadastro.cadastrarLocacao(data, hora);

    assertTrue(this.paginaListaLocacoesCliente.isLocacaoCadastrada(data, hora));
  }

  // R5: Locacao de uma bicicleta, deve enviar emails
  public void deveriaEnviarEmailQuandoUmaLocacoesDeUmClienteForFeita() {

  }

  // R8: Listagem de todas as locacoes de uma locadora
  @Test
  public void deveriaListarLocacoesDeUmaLocadora() {
    this.entrarComoCliente();

    this.paginaDeCadastro = paginaListaLocacoesCliente.carregarFormulario();

    String data = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//     escolher uma hora livre
    String hora = "1000AM";

    this.paginaListaLocacoesCliente = paginaDeCadastro.cadastrarLocacao(data, hora);

    assertTrue(this.paginaListaLocacoesCliente.isLocacaoCadastrada(data, hora));

    this.paginaListaLocacoesCliente.fazerLogout();

    this.entrarComoLocadora();

    assertTrue(this.paginaListaLocacoesLocadora.isLocacaoCadastrada(data, hora));
  }
}
