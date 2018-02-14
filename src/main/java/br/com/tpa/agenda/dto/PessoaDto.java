package br.com.tpa.agenda.dto;

import java.util.List;

public class PessoaDto {
	
	private Long codigo;
	private String nome;
	
	private List<ContatoDto> contatoDto;
	
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

	public List<ContatoDto> getContatoDto() {
		return contatoDto;
	}

	public void setContatoDto(List<ContatoDto> contatoDto) {
		this.contatoDto = contatoDto;
	}
}
