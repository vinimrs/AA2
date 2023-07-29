package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.domain.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{

	Cliente findById(long id);
	
	Cliente findByCpf (String cpf);

	List<Cliente> findAll();
	
	Cliente save(Cliente cliente);

	void deleteById(Long id);


}
