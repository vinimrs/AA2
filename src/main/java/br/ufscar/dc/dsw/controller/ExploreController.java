package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/explorar")
public class ExploreController {

  @Autowired
  private ILocadoraService locadoraService;

  @GetMapping("/locadoras")
  public String listar(@RequestParam(required = false) String city, ModelMap model) {
    
    List<Locadora> locadoras;
    if (city != null && !city.isEmpty()) {
      locadoras = locadoraService.buscarPorCidade(city);
      model.addAttribute("city", city);
    } else {
      locadoras = locadoraService.buscarTodos();
    }

    List<String> cidades = locadoraService.buscarCidades();

    model.addAttribute("cities", cidades);
    model.addAttribute("locadoras", locadoras);
    return "explorar/locadoras";
  }

}
