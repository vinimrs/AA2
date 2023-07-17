package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Locadora;

import java.util.List;

public interface ILocadoraService {

	Locadora buscarPorId(Long id);
	
	List<Locadora> buscarTodos();

	void salvar(Locadora locadora);
	
	void excluir(Long id);
	
}
