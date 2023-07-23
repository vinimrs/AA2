package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


	private Cliente getCliente() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return  clienteService.buscarPorId(usuarioDetails.getUsuario().getId());
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Locacao locacao) {
		LocalDate data = LocalDate.now();
		LocalTime hora = LocalTime.now();
		locacao.setClient(this.getCliente());
		locacao.setDate(data);
		locacao.setHour(hora);

		return "locacao/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {

		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioDetails.getUsuario();

		if(usuario.getRole().equals("CLIENTE")){
			Cliente cliente = clienteService.buscarPorId(usuario.getId());
			model.addAttribute("locacoes",service.buscarPorCliente(cliente));
			model.addAttribute("cliente", cliente);
		}

		if(usuario.getRole().equals("LOCADORA")){
			Locadora locadora = locadoraService.buscarPorId(usuario.getId());
			model.addAttribute("locacoes",service.buscarPorLocadora(locadora));
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
		attr.addFlashAttribute("sucess", "Locacao inserida com sucesso.");
		return "redirect:/locacoes/listar";
	}
	
	@ModelAttribute("locadoras")
	public List<Locadora> listaLocadoras() {
		return locadoraService.buscarTodos();
	}
}
