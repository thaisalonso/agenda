package br.com.tpa.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tpa.agenda.model.Pessoa;

public interface PessoasRepository extends JpaRepository<Pessoa, Long>{

}
