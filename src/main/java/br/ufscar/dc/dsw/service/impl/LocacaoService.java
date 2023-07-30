package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class LocacaoService implements ILocacaoService {

	@Autowired
	ILocacaoDAO dao;

	public void salvar(Locacao locacao) {
		dao.save(locacao);
	}

	public void excluir(Long id) {
		dao.deleteById(id.longValue());
	}

	@Transactional(readOnly = true)
	public Locacao buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Locacao> buscarTodos() {
		return (List<Locacao>) dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Locacao> buscarPorCliente(Cliente cliente) {return dao.findAllByClient(cliente);}

	@Transactional(readOnly = true)
	public List<Locacao> buscarPorLocadora(Locadora locadora) {return dao.findAllByRentalCompany(locadora);}

}
