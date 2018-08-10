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

import br.com.tpa.agenda.model.Endereco;
import br.com.tpa.agenda.model.Pessoa;
import br.com.tpa.agenda.service.EnderecosService;
import br.com.tpa.agenda.service.PessoasService;

@RestController
@RequestMapping("/enderecos")
public class EnderecosController {
	
	@Autowired
	private PessoasService pessoasService;
	
	@Autowired
	private EnderecosService enderecosService;
	
	@PostMapping("/pessoa/{codigoPessoa}")
	public ResponseEntity<Endereco> inserirEndereco(@PathVariable Long codigoPessoa, @Valid @RequestBody Endereco endereco) {
		
		Pessoa pessoa = pessoasService.buscarPessoa(codigoPessoa);
		
		if (pessoa == null) {
			return notFound().build();
		}
		
		endereco.setPessoa(pessoa);
		
		return ok(enderecosService.inserirEndereco(endereco));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long codigo, @Valid @RequestBody Endereco endereco) {
		
		Endereco enderecoExistente = enderecosService.buscarEndereco(codigo);
		
		if (enderecoExistente == null) {
			return notFound().build();
		}
		
		BeanUtils.copyProperties(endereco, enderecoExistente, "codigo");
		enderecoExistente = enderecosService.inserirEndereco(endereco);
		
		return ok(enderecoExistente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Endereco> buscarEndereco(@PathVariable Long codigo) {
		Endereco endereco = enderecosService.buscarEndereco(codigo);
		
		if (endereco == null) {
			return notFound().build();
		}
		return ok(endereco);
		
	}
	
	@GetMapping
	public List<Endereco> listarEnderecos() {
		return enderecosService.listarEnderecos();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirEndereco(@PathVariable Long codigo) {
		Endereco endereco = enderecosService.buscarEndereco(codigo);
		
		if (endereco == null) {
			return notFound().build();
		}
		enderecosService.excluirEndereco(endereco);
		
		return noContent().build();
	}

}
