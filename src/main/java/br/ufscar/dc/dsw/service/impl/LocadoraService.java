package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false) // pois a depender das regras aplicadas aqui, pode ocorrer inconsistências
public class LocadoraService implements ILocadoraService {

	@Autowired
	ILocadoraDAO dao;

	public void salvar(Locadora locadora) {
		dao.save(locadora);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Locadora buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Locadora> buscarTodos() {
		return dao.findAll();
	}
}
