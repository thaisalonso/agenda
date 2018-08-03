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
import br.com.tpa.agenda.service.ContatosService;
import br.com.tpa.agenda.service.PessoasService;

@RestController
@RequestMapping("/contatos")
public class ContatosController {
	
	@Autowired
	private ContatosService contatosService;
	
	@Autowired
	private PessoasService pessoasService;
	
	@PostMapping("/pessoa/{codigoPessoa}")
	public ResponseEntity<Contato> inserirContato(@PathVariable Long codigoPessoa, @Valid @RequestBody Contato contato) {
		
		Pessoa pessoa = pessoasService.buscarPessoa(codigoPessoa);
		
		if (pessoa == null) {
			return notFound().build();
		}
		
		contato.setPessoa(pessoa);
		
		return ok(contatosService.inserirContato(contato));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Contato> atualizarContato(@PathVariable Long codigo, @Valid @RequestBody Contato contato) {
		
		Contato contatoExistente = contatosService.buscarContato(codigo);
		
		if (contatoExistente == null) {
			return notFound().build();
		}
		
		BeanUtils.copyProperties(contato, contatoExistente, "codigo");
		
		contatoExistente = contatosService.inserirContato(contatoExistente);
		
		return ok(contatoExistente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Contato> buscarContato(@PathVariable Long codigo) {
		
		Contato contato = contatosService.buscarContato(codigo);
		
		if (contato == null) {
			return notFound().build();
		}
		
		return ok(contato);
	}
	
	@GetMapping
	public List<Contato> listarContatos() {
		return contatosService.listarContatos();
	}


	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirContato(@PathVariable Long codigo) {
		Contato contato = contatosService.buscarContato(codigo);
		
		if (contato == null) {
			return notFound().build();
		}
		contatosService.excluirContato(contato);
		return noContent().build();
	}
}
