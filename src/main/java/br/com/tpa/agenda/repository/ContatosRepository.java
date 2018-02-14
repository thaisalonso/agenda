package br.com.tpa.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tpa.agenda.model.Contato;

public interface ContatosRepository extends JpaRepository<Contato, Long> {

}
