package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SuppressWarnings("unchecked")
public interface ILocacaoDAO extends CrudRepository<Locacao, Long>{

	Locacao findById(long id);

	List<Locacao> findAllByCliente(Cliente c);

	List<Locacao> findAllByLocadora(Locadora l);

	Locacao save(Locacao locacao);
}