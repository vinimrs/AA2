package br.ufscar.dc.dsw.validation.classlevel;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class UniqueDataHourClientValidator implements ConstraintValidator<ValidLocacao, Locacao> {

  @Autowired
  private ILocacaoDAO dao;

  @Override
  public boolean isValid(Locacao locacao, ConstraintValidatorContext context) {
    if (dao != null) {

      final boolean[] isValid = {true};

      List<Locacao> locacoes = dao.findAllByClient(locacao.getClient());
      locacoes.forEach(locacao1 -> {
        if (locacao1.getDate().equals(locacao.getDate()) && locacao1.getHour().equals(locacao.getHour())) {
          isValid[0] = false;
        }
      });

      // Se a validação falhar, o método isValid retorna false
      // e a mensagem de erro é exibida na página no campo "hour"
      if (!isValid[0]) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
            .addPropertyNode("hour").addConstraintViolation();
      }

      return isValid[0];
    } else {
      // Durante a execução da classe LivrariaMvcApplication
      // não há injeção de dependência
      return true;
    }

  }
}