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

import br.com.tpa.agenda.model.TipoContato;
import br.com.tpa.agenda.service.TipoContatoService;

@RestController
@RequestMapping("/tipoContato")
public class TipoContatoController {
	
	@Autowired
	private TipoContatoService tipoContatoService;
	
	@PostMapping
	public ResponseEntity<TipoContato> inserirTipoContato(@Valid @RequestBody TipoContato tipoContato) {
		return ok(tipoContatoService.inserirTipoContato(tipoContato));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<TipoContato> atualizarTipoContato(@PathVariable Long codigo, @Valid @RequestBody TipoContato tipoContato) {
		
		TipoContato tipoContatoExistente = tipoContatoService.buscarTipoContato(codigo);
		if (tipoContatoExistente == null) {
			return notFound().build();
		}
		
		BeanUtils.copyProperties(tipoContato, tipoContatoExistente, "codigo");
		tipoContatoExistente = tipoContatoService.inserirTipoContato(tipoContato);
		
		return ok(tipoContatoExistente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<TipoContato> buscarTipoContato(@PathVariable Long codigo) {
		TipoContato tipoContato = tipoContatoService.buscarTipoContato(codigo);
		
		if (tipoContato == null) {
			return notFound().build();
		}
		return ok(tipoContato);
	}
	
	@GetMapping
	public List<TipoContato> listarTipoContato() {
		return tipoContatoService.listarTipoContato();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirTipoContato(@PathVariable Long codigo) {
		TipoContato tipoContato = tipoContatoService.buscarTipoContato(codigo);
		
		if (tipoContato == null) {
			return notFound().build();
		}
		tipoContatoService.excluirTipoContato(tipoContato);
		
		return noContent().build();
	}
	
	

}
