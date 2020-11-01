package br.com.restaurante.apirestrestaurante.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.restaurante.apirestrestaurante.model.Prato;
import br.com.restaurante.apirestrestaurante.model.Restaurante;
import br.com.restaurante.apirestrestaurante.repository.PratoRepository;
import br.com.restaurante.apirestrestaurante.repository.RestauranteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "API REST Prato")
@RequestMapping("/api/prato")
public class PratoController {

	@Autowired
	private PratoRepository repository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@ApiOperation(value = "Lista de Pratos")
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<List<Prato>> findAllPratos() {
		try {
			List<Prato> list = this.repository.findAll();
			if (list.isEmpty()) {
				return new ResponseEntity<List<Prato>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Prato>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Prato>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Encontra um Prato por Id")
	@GetMapping(value = "{id}", produces = { "application/json" })
	public ResponseEntity<Prato> findByIdPrato(@PathVariable(value = "id") Long id) {
		try {
			Optional<Prato> prato = this.repository.findById(id);
			if (!prato.isPresent()) {
				return new ResponseEntity<Prato>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Prato>(prato.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Prato>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Cria um Prato")
	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Prato> createPrato(@RequestBody @Valid Prato prato) {
		try {
			Optional<Restaurante> restaurante = this.restauranteRepository.findById(prato.getIdRestaurante().getId());
			if (!restaurante.isPresent()) {
				return new ResponseEntity<Prato>(HttpStatus.BAD_REQUEST);
			}
			Prato novo = this.repository.save(prato);
			return new ResponseEntity<Prato>(novo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Prato>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Altera um Prato")
	@PutMapping(produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Prato> alterPrato(@RequestBody @Valid Prato prato) {
		try {
			Optional<Prato> itemDb = this.repository.findById(prato.getId());
			if (!itemDb.isPresent()) {
				return new ResponseEntity<Prato>(HttpStatus.NOT_FOUND);
			}
			Optional<Restaurante> restaurante = this.restauranteRepository.findById(prato.getIdRestaurante().getId());
			if (!restaurante.isPresent()) {
				return new ResponseEntity<Prato>(HttpStatus.BAD_REQUEST);
			}
			Prato novo = this.repository.save(prato);
			return new ResponseEntity<Prato>(novo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Prato>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Deleta um Prato por Id")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Prato> deletePratoById(@PathVariable(value = "id") Long id) {
		try {
			Optional<Prato> itemDb = this.repository.findById(id);
			if (!itemDb.isPresent()) {
				return new ResponseEntity<Prato>(HttpStatus.NOT_FOUND);
			}
			this.repository.deleteById(id);
			return new ResponseEntity<Prato>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Prato>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
