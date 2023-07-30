package br.ufscar.dc.dsw.validation.uniques;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

  @Autowired
  private IClienteDAO dao;

  @Override
  public boolean isValid(String CPF, ConstraintValidatorContext context) {
    if (dao != null) {
      Cliente cliente = dao.findByCpf(CPF);
      return cliente == null;
    } else {
      // Durante a execução da classe LocadoraMVC
      // não há injeção de dependência
      return true;
    }

  }
}