package br.com.tpa.agenda.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.noContent;

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
import br.com.tpa.agenda.model.Telefone;
import br.com.tpa.agenda.service.PessoasService;
import br.com.tpa.agenda.service.TelefonesService;

@RestController
@RequestMapping("/telefones")
public class TelefonesController {
	
	@Autowired
	private PessoasService pessoasService;
	
	@Autowired
	private TelefonesService telefonesService;
	
	@PostMapping("/pessoa/{codigoPessoa}")
	public ResponseEntity<Telefone> inserirTelefone(@PathVariable Long codigoPessoa, @Valid @RequestBody Telefone telefone) {
		
		Pessoa pessoa = pessoasService.buscarPessoa(codigoPessoa);
		
		if (pessoa == null) {
			return notFound().build();
		}
		
		telefone.setPessoa(pessoa);
		
		return ok(telefonesService.inserirTelefone(telefone));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Telefone> atualizarTelefone(@PathVariable Long codigo, @Valid @RequestBody Telefone telefone) {
		
		Telefone telefoneExistente = telefonesService.buscarTelefone(codigo);
		
		if (telefoneExistente == null) {
			return notFound().build();
		}
		
		BeanUtils.copyProperties(telefone, telefoneExistente, "codigo");
		telefoneExistente = telefonesService.inserirTelefone(telefone);
		
		return ok(telefoneExistente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Telefone> buscarTelefone(@PathVariable Long codigo) {
		Telefone telefone = telefonesService.buscarTelefone(codigo);
		
		if (telefone == null) {
			return notFound().build();
		}
		return ok(telefone);
		
	}
	
	@GetMapping
	public List<Telefone> listarTelefones() {
		return telefonesService.listarTelefones();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirTelefone(@PathVariable Long codigo) {
		Telefone telefone = telefonesService.buscarTelefone(codigo);
		
		if (telefone == null) {
			return notFound().build();
		}
		telefonesService.excluirTelefone(telefone);
		
		return noContent().build();
	}

}
