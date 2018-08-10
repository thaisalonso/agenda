package br.com.tpa.agenda.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tpa.agenda.model.Email;
import br.com.tpa.agenda.model.Pessoa;
import br.com.tpa.agenda.service.EmailsService;
import br.com.tpa.agenda.service.PessoasService;

@RestController
@RequestMapping("/emails")
public class EmailsController {
	
	@Autowired
	private PessoasService pessoasService;
	
	@Autowired
	private EmailsService emailsService;
	
	@PostMapping("/pessoa/{codigoPessoa}")
	public ResponseEntity<Email> inseriremail(@PathVariable Long codigoPessoa, @Valid @RequestBody Email email) {
		
		Pessoa pessoa = pessoasService.buscarPessoa(codigoPessoa);
		
		if (pessoa == null) {
			return notFound().build();
		}
		
		email.setPessoa(pessoa);
		
		return ok(emailsService.inserirEmail(email));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Email> atualizaremail(@PathVariable Long codigo, @Valid @RequestBody Email email) {
		
		Email emailExistente = emailsService.buscarEmail(codigo);
		
		if (emailExistente == null) {
			return notFound().build();
		}
		
		BeanUtils.copyProperties(email, emailExistente, "codigo");
		emailExistente = emailsService.inserirEmail(email);
		
		return ok(emailExistente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Email> buscaremail(@PathVariable Long codigo) {
		Email email = emailsService.buscarEmail(codigo);
		
		if (email == null) {
			return notFound().build();
		}
		return ok(email);
		
	}
	
	@GetMapping
	public List<Email> listaremails() {
		return emailsService.listarEmails();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirEmail(@PathVariable Long codigo) {
		Email email = emailsService.buscarEmail(codigo);
		
		if (email == null) {
			return notFound().build();
		}
		emailsService.excluirEmail(email);
		
		return noContent().build();
	}

}
