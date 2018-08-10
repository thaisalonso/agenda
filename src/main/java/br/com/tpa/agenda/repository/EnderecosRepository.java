package br.com.tpa.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tpa.agenda.model.Endereco;

public interface EnderecosRepository extends JpaRepository<Endereco, Long>{

}
