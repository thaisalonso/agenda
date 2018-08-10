package br.com.tpa.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tpa.agenda.model.Telefone;

public interface TelefonesRepository extends JpaRepository<Telefone, Long> {

}
