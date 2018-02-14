package br.com.tpa.agenda.dto;

public class ContatoDto {

	private Long codigo;
	private Long telefone;
	private String email;
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Long getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
