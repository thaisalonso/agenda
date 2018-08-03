package br.com.tpa.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tpa.agenda.model.Pessoa;
import br.com.tpa.agenda.repository.PessoasRepository;

@Service
public class PessoasService {
	
	@Autowired
	private PessoasRepository pessoas;
	
	public Pessoa inserirPessoa(Pessoa pessoa) {
		return pessoas.save(pessoa);
	}
	
	public Pessoa buscarPessoa(Long codigo) {
		Pessoa pessoa = pessoas.findOne(codigo);
		return pessoa;
	}
	
	public List<Pessoa> listarPessoas() {
		return pessoas.findAll();
	}
	
	public void excluirPessoa(Pessoa pessoa) {
		pessoas.delete(pessoa);
	}

}
