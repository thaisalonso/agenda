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

import br.com.tpa.agenda.model.Contato;
import br.com.tpa.agenda.model.Pessoa;
import br.com.tpa.agenda.repository.ContatosRepository;
import br.com.tpa.agenda.repository.PessoasRepository;

@RestController
@RequestMapping("/contatos")
public class ContatosController {
	
	@Autowired
	private ContatosRepository contatos;
	
	@Autowired
	private PessoasRepository pessoas;
	
	@PostMapping("/pessoa/{codigoPessoa}")
	public ResponseEntity<Contato> inserirContato(@PathVariable Long codigoPessoa, @Valid @RequestBody Contato contato) {
		
		Pessoa pessoa = pessoas.findOne(codigoPessoa);
		
		if (pessoa == null) {
			return notFound().build();
		}
		
		contato.setPessoa(pessoa);
		
		return ok(contatos.save(contato));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Contato> atualizarContato(@PathVariable Long codigo, @Valid @RequestBody Contato contato) {
		
		Contato contatoExistente = contatos.findOne(codigo);
		
		if (contatoExistente == null) {
			return notFound().build();
		}
		
		BeanUtils.copyProperties(contato, contatoExistente, "codigo");
		
		contatoExistente = contatos.save(contatoExistente);
		
		return ok(contatoExistente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Contato> buscarContato(@PathVariable Long codigo) {
		
		Contato contato = contatos.findOne(codigo);
		
		if (contato == null) {
			return notFound().build();
		}
		
		return ok(contato);
	}
	
	@GetMapping
	public List<Contato> listarContatos() {
		return contatos.findAll();
	}


	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirContato(@PathVariable Long codigo) {
		
		Contato contato = contatos.findOne(codigo);
		
		if (contato == null) {
			return notFound().build();
		}
		
		contatos.delete(contato);
		
		return noContent().build();
	}
}
