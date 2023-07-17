package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IAdminDAO;
import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.service.spec.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class AdminService implements IAdminService {

	@Autowired
	IAdminDAO dao;

	public void salvar(Admin admin) {
		dao.save(admin);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Admin buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Admin> buscarTodos() {
		return dao.findAll();
	}
}
