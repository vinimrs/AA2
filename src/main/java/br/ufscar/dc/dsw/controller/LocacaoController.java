package br.ufscar.dc.dsw.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import br.ufscar.dc.dsw.util.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

  @Autowired
  private ILocacaoService service;

  @Autowired
  private IClienteService clienteService;

  @Autowired
  private ILocadoraService locadoraService;

  @Autowired
  ServletContext context;

  private String host;
  private String port;
  private String user;
  private String pass;

  @PostConstruct
  public void initialize() {
    // reads SMTP server setting from web.xml file
    host = context.getInitParameter("host");
    port = context.getInitParameter("port");
    user = context.getInitParameter("user");
    pass = context.getInitParameter("pass");
  }

  private Cliente getCliente() {
    UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return clienteService.buscarPorId(usuarioDetails.getUsuario().getId());
  }

  @ModelAttribute
  public void addAttributes(Model model) {
    List<Locadora> locadoras;
    locadoras = listaLocadoras();
    model.addAttribute("locadoras", locadoras);

    LocalTime minHora = LocalTime.now().withMinute(0).withSecond(0).withNano(0).plusHours(1);
    model.addAttribute("minHora", minHora.toString());

    String minData = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    model.addAttribute("minData", minData);

    Cliente cliente = getCliente();
    model.addAttribute("cliente", cliente);
  }

  @GetMapping("/cadastrar")
  public String cadastrar(Locacao locacao) {
    LocalDate data = LocalDate.now();

    // Hour always with HH:00
    LocalTime hora = LocalTime.now().withMinute(0).plusHours(1);

    Cliente cliente = getCliente();

    if (cliente == null) {
      return "redirect:/locacoes/listar";
    }

    locacao.setClient(cliente);
    locacao.setDate(data);
    locacao.setHour(hora);

    return "locacao/cadastro";
  }

  @GetMapping("/listar")
  public String listar(ModelMap model) {

    UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Usuario usuario = usuarioDetails.getUsuario();

    if (usuario.getRole().equals("ROLE_CLIENTE")) {
      Cliente cliente = clienteService.buscarPorId(usuario.getId());
      model.addAttribute("locacoes", service.buscarPorCliente(cliente));
      model.addAttribute("cliente", cliente);
    }

    if (usuario.getRole().equals("ROLE_LOCADORA")) {
      Locadora locadora = locadoraService.buscarPorId(usuario.getId());
      model.addAttribute("locacoes", service.buscarPorLocadora(locadora));
      model.addAttribute("locadora", locadora);
    }

    return "locacao/lista";
  }

  @PostMapping("/salvar")
  public String salvar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr) {

    if (result.hasErrors()) {
      return "locacao/cadastro";
    }

    service.salvar(locacao);
    attr.addFlashAttribute("sucess", "locacao.create.sucess");

    try {

      Cliente cliente = locacao.getClient();
      Locadora locadora = locacao.getRentalCompany();

      String emailCliente = cliente.getEmail();
      String emailLocadora = locadora.getEmail();

      System.out.println(emailCliente + emailLocadora);

      String emailDoSistema = "locadoradebicicletasdsw1@gmail.com";
      String assuntoCliente = "Seu aluguel foi aprovado!";
      String conteudoCliente = "Aproveite a sua bicicleta alugada na data " + locacao.getDate() +
          " às " + locacao.getHour() +
          " na locadora " + locadora.getName() + "!";

      String assuntoLocadora = "Um aluguel foi aprovado!";
      String conteudoLocadora = "O cliente " + cliente.getName() + " alugou uma bicicleta na data " + locacao.getDate() +
          " às " + locacao.getHour() +
          " na sua locadora!";


      String resultMessage = "";

      try {
        EmailUtility.sendEmail(host, port, user, pass, emailCliente, assuntoCliente,
            conteudoCliente, emailDoSistema);
        EmailUtility.sendEmail(host, port, user, pass, emailLocadora, assuntoLocadora,
            conteudoLocadora, emailDoSistema);
        resultMessage = "A sua locacao foi cadastrada com sucesso!";
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/locacoes/listar";
  }

  @GetMapping("/editar/{id}")
  public String preEditar(@PathVariable("id") Long id, ModelMap model) {
    model.addAttribute("locacao", service.buscarPorId(id));
    return "locacao/cadastro";
  }

  @PostMapping("/editar")
  public String editar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr) {

    service.salvar(locacao);
    attr.addFlashAttribute("sucess", "locacao.edit.sucess");
    return "redirect:/locacoes/listar";
  }

  @GetMapping("/excluir/{id}")
  public String excluir(@PathVariable("id") Long id, ModelMap model) {
    service.excluir(id);
    model.addAttribute("sucess", "locacao.delete.sucess");
    return listar(model);
  }

  public List<Locadora> listaLocadoras() {
    return locadoraService.buscarTodos();
  }
}
