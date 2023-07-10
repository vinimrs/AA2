package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Locadora;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SuppressWarnings("unchecked")
public interface ILocadoraDAO extends CrudRepository<Locadora, Long>{

	Locadora findById(long id);

	Locadora findByCnpj(String cnpj);

	List<Locadora> findAll();
	
	Locadora save(Locadora locadora);

	void deleteById(Long id);
}