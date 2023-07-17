package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;

import java.util.List;

public interface ILocacaoService {

	Locacao buscarPorId(Long id);

	List<Locacao> buscarPorLocadora(Locadora locadora);

	List<Locacao> buscarPorCliente(Cliente cliente);
	
	List<Locacao> buscarTodos();
	
	void salvar(Locacao Locacao);
	
	void excluir(Long id);
	
}
