package br.com.tpa.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tpa.agenda.model.Endereco;
import br.com.tpa.agenda.repository.EnderecosRepository;

@Service
public class EnderecosService {
	
	@Autowired
	private EnderecosRepository enderecos;
	
	public Endereco inserirEndereco(Endereco endereco) {
		return enderecos.save(endereco);
	}
	
	public Endereco buscarEndereco(Long codigo) {
		Endereco endereco = enderecos.findOne(codigo);
		return endereco;
	}
	
	public List<Endereco> listarEnderecos() {
		return enderecos.findAll();
	}
	
	public void excluirEndereco(Endereco endereco) {
		enderecos.delete(endereco);
	}
	

}
