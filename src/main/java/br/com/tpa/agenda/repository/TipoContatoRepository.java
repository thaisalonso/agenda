package br.com.tpa.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tpa.agenda.model.TipoContato;

public interface TipoContatoRepository extends JpaRepository<TipoContato, Long>{

}
