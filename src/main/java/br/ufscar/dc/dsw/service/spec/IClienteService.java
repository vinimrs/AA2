package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Cliente;

import java.util.List;

public interface IClienteService {

	Cliente buscarPorId(Long id);

	List<Cliente> buscarTodos();

	void salvar(Cliente cliente);

	void excluir(Long id);	
}
