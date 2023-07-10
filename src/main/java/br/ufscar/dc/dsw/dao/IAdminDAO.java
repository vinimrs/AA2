package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings("unchecked")
public interface IAdminDAO extends CrudRepository<Admin, Long> {
	
	Admin findById(long id);

	List<Admin> findAll();
	
	Admin save(Admin usuario);

	void deleteById(Long id);
	
    @Query("SELECT u FROM Admin u WHERE u.username = :username")
    public Admin getAdminByUsername(@Param("username") String username);
}