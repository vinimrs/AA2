package br.ufscar.dc.dsw.validation;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (dao != null) {
            Usuario usuario = dao.getUserByEmail(email);
            return usuario == null;
        } else {
            // Durante a execução da classe LivrariaMvcApplication
            // não há injeção de dependência
            return true;
        }

    }
}