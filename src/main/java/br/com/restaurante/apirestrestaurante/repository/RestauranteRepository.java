package br.com.restaurante.apirestrestaurante.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restaurante.apirestrestaurante.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{

	public List<Restaurante> findByNomeContainingIgnoreCase(String nome);
	
	public List<Restaurante> findAll(Sort sort);

}
