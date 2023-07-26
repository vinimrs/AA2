package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SuppressWarnings("unchecked")
public interface ILocadoraDAO extends CrudRepository<Locadora, Long> {

  Locadora findById(long id);

  Locadora findByCnpj(String cnpj);

  Locadora findByEmail(String email);

  List<Locadora> findAll();

  List<Locadora> findAllByCity(String city);

  Locadora save(Locadora locadora);

  void deleteById(Long id);

  @Query("SELECT DISTINCT l.city FROM Locadora l")
  List<String> findAllCities();
}