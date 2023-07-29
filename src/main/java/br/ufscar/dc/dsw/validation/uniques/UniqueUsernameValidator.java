package br.ufscar.dc.dsw.validation.uniques;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

  @Autowired
  private IUsuarioDAO dao;

  @Override
  public boolean isValid(String username, ConstraintValidatorContext context) {
    if (dao != null) {
      Usuario usuario = dao.getUserByUsername(username);
      return usuario == null;
    } else {
      // Durante a execução da classe LivrariaMvcApplication
      // não há injeção de dependência
      return true;
    }

  }
}