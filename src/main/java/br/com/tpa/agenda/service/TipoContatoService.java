package br.com.tpa.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tpa.agenda.model.TipoContato;
import br.com.tpa.agenda.repository.TipoContatoRepository;

@Service
public class TipoContatoService {
	
	@Autowired
	private TipoContatoRepository tipoContatoRepository;
	
	public TipoContato inserirTipoContato(TipoContato tipoContato) {
		return tipoContatoRepository.save(tipoContato);
	}
	
	public TipoContato buscarTipoContato(Long codigo) {
		TipoContato tipoContato = tipoContatoRepository.findOne(codigo);
		return tipoContato;
	}
	
	public List<TipoContato> listarTipoContato() {
		return tipoContatoRepository.findAll();
	}
	
	public void excluirTipoContato(TipoContato tipoContato) {
		tipoContatoRepository.delete(tipoContato);
	}

}
