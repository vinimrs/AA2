package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IAdminService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarTodos());
		return "cliente/lista";
	}
	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "cliente/cadastro";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				// Convert the String date from the HTML form to LocalDate
				// Adjust the date pattern as per your HTML date input format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(text, formatter);
				setValue(date);
			}
		});
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "cliente/cadastro";
		}

		cliente.setPassword(encoder.encode(cliente.getPassword()));
		clienteService.salvar(cliente);
		attr.addFlashAttribute("success", "cliente.create.success");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", clienteService.buscarPorId(id));
		return "cliente/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cliente Cliente, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "cliente/cadastro";
		}

		clienteService.salvar(Cliente);
		attr.addFlashAttribute("sucess", "cliente.edit.sucess");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		clienteService.excluir(id);
		attr.addFlashAttribute("sucess", "cliente.delete.sucess"); // atributo para enviar mensagem de sucesso no
		// redirect
		return "redirect:/clientes/listar";
	}

	// este atrtbuto será geral e poderá ser acessado por todas as views que utilizem este controller
//	@ModelAttribute("locadoras")
//	public List<Locadora> listaLocadoras() {
//		return locadoraService.buscarTodos();
//	}
}
