package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.service.spec.ILocacaoService;

@Controller
@RequestMapping("/locacao")
public class LocacaoController {

	@Autowired
	private ILocacaoService locacaoService;

	@Autowired
	private IClienteService editoraService;

	@GetMapping("/cadastrar")
	public String cadastrar(Locacao Locacao) {
		return "locacao/cadastro";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "locacao/cadastro";
		}

		locacaoService.salvar(locacao);
		attr.addFlashAttribute("success", "Locacao.create.success");
		return "redirect:/cliente/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("Locacao", locacaoService.buscarPorId(id));
		return "Locacao/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Locacao Locacao, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "Locacao/cadastro";
		}

		locacaoService.salvar(Locacao);
		attr.addFlashAttribute("sucess", "Locacao.edit.sucess");
		return "redirect:/Locacaos/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		locacaoService.excluir(id);
		attr.addFlashAttribute("sucess", "Locacao.delete.sucess");
		return "redirect:/Locacaos/listar";
	}

	@ModelAttribute("editoras")
	public List<Editora> listaEditoras() {
		return editoraService.buscarTodos();
	}
}
