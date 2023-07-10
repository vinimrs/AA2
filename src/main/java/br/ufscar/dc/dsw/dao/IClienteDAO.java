package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{

	Cliente findById(long id);
	
	Cliente findByCpf (String cpf);

	List<Cliente> findAll();
	
	Cliente save(Cliente cliente);

	void deleteById(Long id);
}
