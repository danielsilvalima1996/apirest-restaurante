package br.com.restaurante.apirestrestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restaurante.apirestrestaurante.model.Prato;

public interface PratoRepository extends JpaRepository<Prato, Long>{

}
