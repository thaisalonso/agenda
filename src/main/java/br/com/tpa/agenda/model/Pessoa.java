package br.com.tpa.agenda.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	private String nome;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	public List<Telefone> telefone;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	public List<Endereco> endereco;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Email> email;
	
	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Email> getEmail() {
		return email;
	}

	public void setEmail(List<Email> email) {
		this.email = email;
	}
	
	
}
