package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Admin;

import java.util.List;

public interface IAdminService {

	Admin buscarPorId(Long id);
	
	List<Admin> buscarTodos();
	
	void salvar(Admin admin);
	
	void excluir(Long id);
	
}
