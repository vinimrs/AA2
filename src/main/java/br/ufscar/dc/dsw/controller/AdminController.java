package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.service.spec.IAdminService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admins")
public class AdminController {

  @Autowired
  private IAdminService adminService;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @GetMapping("/listar")
  public String listar(ModelMap model) {
    model.addAttribute("admins", adminService.buscarTodos());
    return "admin/lista";
  }

  @GetMapping("/cadastrar")
  public String cadastrar(Admin admin) {
    return "admin/cadastro";
  }

  @PostMapping("/salvar")
  public String salvar(@Valid Admin admin, BindingResult result, RedirectAttributes attr) {
    if (result.hasErrors()) {
      return "admin/cadastro";
    }

    admin.setPassword(encoder.encode(admin.getPassword()));
    adminService.salvar(admin);
    attr.addFlashAttribute("sucess", "admin.create.sucess");
    return "redirect:/admins/listar";
  }

  @GetMapping("/editar/{id}")
  public String preEditar(@PathVariable("id") Long id, ModelMap model) {
    model.addAttribute("admin", adminService.buscarPorId(id));
    return "admin/cadastro";
  }

  @PostMapping("/editar")
  public String editar(@Valid Admin Admin, BindingResult result, RedirectAttributes attr) {

    // Apenas rejeita se o problema não for com o CPF, EMAIL ou USERNAME (Read-onlys)
    if (result.getFieldErrorCount() > 2
        || result.getFieldError("email") == null
        || result.getFieldError("username") == null) {
      return "admin/cadastro";
    }

    adminService.salvar(Admin);
    attr.addFlashAttribute("sucess", "admin.edit.sucess");
    return "redirect:/admins/listar";
  }

  @GetMapping("/excluir/{id}")
  public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
    adminService.excluir(id);
    attr.addFlashAttribute("sucess", "admin.delete.sucess"); // atributo para enviar mensagem de sucesso no
    // redirect
    return "redirect:/admins/listar";
  }

  // este atrtbuto será geral e poderá ser acessado por todas as views que utilizem este controller
//	@ModelAttribute("locadoras")
//	public List<Locadora> listaLocadoras() {
//		return locadoraService.buscarTodos();
//	}
}
