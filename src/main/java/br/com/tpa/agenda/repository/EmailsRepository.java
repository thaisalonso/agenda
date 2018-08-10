package br.com.tpa.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tpa.agenda.model.Email;

public interface EmailsRepository extends JpaRepository<Email, Long> {

}
