package br.ufscar.dc.dsw.service.spec;

import java.util.List;

public interface IEditoraService {

	Editora buscarPorId(Long id);

	List<Editora> buscarTodos();

	void salvar(Editora editora);

	void excluir(Long id);
	
	boolean editoraTemLivros(Long id);
}
