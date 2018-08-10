package br.com.tpa.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tpa.agenda.model.Telefone;
import br.com.tpa.agenda.repository.TelefonesRepository;

@Service
public class TelefonesService {
	
	@Autowired
	private TelefonesRepository telefones;
	
	public Telefone inserirTelefone(Telefone telefone) {
		return telefones.save(telefone);
	}
	
	public Telefone buscarTelefone(Long codigo) {
		Telefone telefone = telefones.findOne(codigo);
		return telefone;
	}
	
	public List<Telefone> listarTelefones() {
		return telefones.findAll();
	}
	
	public void excluirTelefone(Telefone telefone) {
		telefones.delete(telefone);
	}

}
