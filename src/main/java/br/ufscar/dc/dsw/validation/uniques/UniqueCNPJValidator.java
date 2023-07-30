package br.ufscar.dc.dsw.validation.uniques;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {

  @Autowired
  private ILocadoraDAO dao;

  @Override
  public boolean isValid(String CNPJ, ConstraintValidatorContext context) {
    if (dao != null) {
      Locadora locadora = dao.findByCnpj(CNPJ);
      return locadora == null;
    } else {
      // Durante a execução da classe LivrariaMvcApplication
      // não há injeção de dependência
      return true;
    }

  }
}