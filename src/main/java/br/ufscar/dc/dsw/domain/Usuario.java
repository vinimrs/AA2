package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

//@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public abstract class Usuario extends AbstractEntity<Long> {
  
//	@NotBlank
	@Column(length = 20, unique = true)
	private String username;

	@NotBlank
	@Column(nullable = false, length = 40, unique = true)
	private String email;

	@NotBlank
	@Column(nullable = false, length = 64)
	private String password;

	@NotBlank
	@Column(nullable = false, length = 60)
	private String name;

	@NotBlank
	@Column(nullable = false, length = 20)
	protected String role;

	@Column(nullable = false)
	private boolean enabled;
		
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}