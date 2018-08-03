package br.com.tpa.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tpa.agenda.model.Contato;
import br.com.tpa.agenda.repository.ContatosRepository;

@Service
public class ContatosService {
	
	@Autowired
	private ContatosRepository contatos;
	
	public Contato inserirContato(Contato contato) {
		return contatos.save(contato);
	}
	
	public Contato buscarContato(Long codigo) {
		Contato contato = contatos.findOne(codigo);
		return contato;
	}
	
	public List<Contato> listarContatos() {
		return contatos.findAll();
	}
	
	public void excluirContato(Contato contato) {
		contatos.delete(contato);
	}

}
