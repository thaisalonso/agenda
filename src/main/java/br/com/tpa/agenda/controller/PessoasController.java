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

import br.com.tpa.agenda.model.Pessoa;
import br.com.tpa.agenda.repository.PessoasRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {
	
	@Autowired
	private PessoasRepository pessoas;
	
	@PostMapping
	public Pessoa inserirPessoa(@Valid @RequestBody Pessoa pessoa) {
		return pessoas.save(pessoa);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaExistente = pessoas.findOne(codigo);
		
		if (pessoaExistente == null) {
			return notFound().build();
		}
		BeanUtils.copyProperties(pessoa, pessoaExistente, "codigo");
		
		pessoaExistente = pessoas.save(pessoaExistente);
		
		return ok(pessoaExistente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPessoa(@PathVariable Long codigo) {
		
		Pessoa pessoa = pessoas.findOne(codigo);
		if (pessoa == null) {
			return notFound().build();
		}
		
		return ok(pessoa);
	}
	
	@GetMapping
	public List<Pessoa> listarPessoas() {
		return pessoas.findAll();
	}


	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirPessoa(@PathVariable Long codigo) {
		
		Pessoa pessoa = pessoas.findOne(codigo);
		if (pessoa == null) {
			return notFound().build();
		}
		pessoas.delete(pessoa);
		
		return noContent().build();
	}
}
