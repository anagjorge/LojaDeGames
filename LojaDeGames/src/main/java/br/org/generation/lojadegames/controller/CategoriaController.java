package br.org.generation.lojadegames.controller;

import java.util.List;

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

import br.org.generation.lojadegames.model.Categoria;
import br.org.generation.lojadegames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
		
	@GetMapping
	private ResponseEntity<List<Categoria>> findAllCategoria(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable long id) {
		return categoriaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/genero/{genero}")
	public ResponseEntity<List<Categoria>> findByTitulo(@PathVariable String genero){
		return ResponseEntity.ok(categoriaRepository.findAllByGeneroContainingIgnoreCase(genero));
							
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria){
		return categoriaRepository.findById(categoria.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());			
	}

	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id) {
		categoriaRepository.deleteById(id);
	}

	
}


