package br.com.restaurante.apirestrestaurante.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurante.apirestrestaurante.model.Restaurante;
import br.com.restaurante.apirestrestaurante.repository.RestauranteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "API REST Restaurante")
@RequestMapping("/api/restaurante")
public class RestauranteController {

	@Autowired
	private RestauranteRepository repository;

	@ApiOperation(value = "Lista de Restaurantes")
	@GetMapping(value = "list", produces = { "application/json" })
	public ResponseEntity<List<Restaurante>> findAllRestaurantes() {
		try {
			List<Restaurante> list = this.repository.findAll();
			if (list.isEmpty()) {
				return new ResponseEntity<List<Restaurante>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Restaurante>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Lista de Restaurantes com o filtro por nome Restaurante")
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<List<Restaurante>> findByNomeRestaurantes(@Param(value = "nome") String nome) {
		try {
			List<Restaurante> list = this.repository.findByNomeContainingIgnoreCase(nome);
			if (list.isEmpty()) {
				return new ResponseEntity<List<Restaurante>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Restaurante>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Encontra um Restaurante por Id")
	@GetMapping(value = "{id}", produces = { "application/json" })
	public ResponseEntity<Restaurante> findByIdRestaurante(@PathVariable(value = "id") Long id) {
		try {
			Optional<Restaurante> restaurante = this.repository.findById(id);
			if (!restaurante.isPresent()) {
				return new ResponseEntity<Restaurante>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Restaurante>(restaurante.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Cria um Restaurante")
	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Restaurante> createRestaurante(@RequestBody @Valid Restaurante restaurante) {
		try {
			Restaurante novo = this.repository.save(restaurante);
			return new ResponseEntity<Restaurante>(novo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Altera um Restaurante")
	@PutMapping(produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Restaurante> alterRestaurante(@RequestBody @Valid Restaurante restaurante) {
		try {
			Optional<Restaurante> itemDb = this.repository.findById(restaurante.getId());
			if (!itemDb.isPresent()) {
				return new ResponseEntity<Restaurante>(HttpStatus.NOT_FOUND);
			}
			Restaurante novo = this.repository.save(restaurante);
			return new ResponseEntity<Restaurante>(novo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Deleta um restaurante por Id")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Restaurante> deleteRestauranteById(@PathVariable(value = "id") Long id) {
		try {
			Optional<Restaurante> itemDb = this.repository.findById(id);
			if (!itemDb.isPresent()) {
				return new ResponseEntity<Restaurante>(HttpStatus.NOT_FOUND);
			}
			this.repository.deleteById(id);
			return new ResponseEntity<Restaurante>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
