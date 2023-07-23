package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false) // pois a depender das regras aplicadas aqui, pode ocorrer inconsistências (sobreescrita)
public class LocadoraService implements ILocadoraService {

	@Autowired
	ILocadoraDAO dao;

	@Autowired
	ILocacaoDAO locacaoDAO;

	public void salvar(Locadora locadora) {
		dao.save(locadora);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Locacao> buscarLocacoesPorCnpj(String cnpj) {
		return locacaoDAO.findAllByRentalCompany(dao.findByCnpj(cnpj));
	}

	@Transactional(readOnly = true)
	public List<Locadora> buscarPorCidade(String city) {
		return dao.findAllByCity(city);
	}

	@Transactional(readOnly = true)
	public Locadora buscarPorCNPJ(String cnpj) {
		return dao.findByCnpj(cnpj);
	}

	@Transactional(readOnly = true)
	public Locadora buscarPorEmail(String email) {
		return dao.findByEmail(email);
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
