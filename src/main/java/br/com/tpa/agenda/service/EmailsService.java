package br.com.tpa.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tpa.agenda.model.Email;
import br.com.tpa.agenda.repository.EmailsRepository;

@Service
public class EmailsService {
	
	@Autowired
	private EmailsRepository emails;
	
	public Email inserirEmail(Email email) {
		return emails.save(email);
	}
	
	public Email buscarEmail(Long codigo) {
		Email email = emails.findOne(codigo);
		return email;
	}
	
	public List<Email> listarEmails() {
		return emails.findAll();
	}
	
	public void excluirEmail(Email email) {
		emails.delete(email);
	}

}
