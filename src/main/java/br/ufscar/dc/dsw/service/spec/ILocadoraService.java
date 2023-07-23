package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;

import java.util.List;

public interface ILocadoraService {

	Locadora buscarPorId(Long id);
	
	List<Locadora> buscarTodos();

	void salvar(Locadora locadora);
	
	void excluir(Long id);

	public List<Locacao> buscarLocacoesPorCnpj(String cnpj);

	public List<Locadora> buscarPorCidade(String city);

	public Locadora buscarPorCNPJ(String cnpj);

	public Locadora buscarPorEmail(String email);

	
}
