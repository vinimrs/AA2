package br.ufscar.dc.dsw.service.spec;

import java.util.List;

public interface ILivroService {

	Livro buscarPorId(Long id);
	
	List<Livro> buscarTodos();
	
	void salvar(Livro livro);
	
	void excluir(Long id);
	
}
