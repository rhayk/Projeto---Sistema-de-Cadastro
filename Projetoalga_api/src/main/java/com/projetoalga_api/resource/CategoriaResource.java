package com.projetoalga_api.resource;



import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoalga_api.event.RecursoCriadoEvent;
import com.projetoalga_api.model.Categoria;
import com.projetoalga_api.repository.CategoriaRepository;






@RestController

@RequestMapping("/categoria")

public class CategoriaResource {



	@Autowired

	private CategoriaRepository categoriaRepository;

	

	@Autowired

	private ApplicationEventPublisher publisher;

	

	@GetMapping

	public List<Categoria> listar() {

		return categoriaRepository.findAll();

	}

	

	@PostMapping

	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {

		Categoria categoriaSalva = categoriaRepository.save(categoria);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);

	}

	

	@GetMapping("/{codigo}")

	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {

		 Categoria categoria = categoriaRepository.findOne(codigo);

		 return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();

	}

	

}


//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import com.projetoalga_api.event.RecursoCriadoEvent;
//import com.projetoalga_api.model.Categoria;
//import com.projetoalga_api.repository.CategoriaRepository;
//
//@RestController                              // ->> retorno ja vai ser JASON
//@RequestMapping("/categoria")               //->> mapeamento da requisição 
//public class CategoriaResource {
//	
//	@Autowired    //O "new" não funciona em interface e o objeto precisa ser enjetado -> então o spring - encontre uma implementação de categoria de repositorio e entregue aqui.
//	private CategoriaRepository categoriaRepository;
//	
//	
//	@Autowired // ORGANIZA OS EVENTOS
//	private ApplicationEventPublisher publisher;
//	
//	@GetMapping                            // ->> saber qual método escolher
//	public ResponseEntity<?> listar(){       //-> retorna a lista de categoria
//	List<Categoria> categoria = categoriaRepository.findAll();
//		return !categoria.isEmpty() ?  ResponseEntity.ok(categoria) : ResponseEntity.noContent().build(); //se tiver elementos na categoria vai retornar normal, quando não tiver vai retornar o o 204 "Aplicação rodou certo, mas é uma lista vazia"
//	}
//	 
//	@PostMapping                              //spring consegue receber objeto de "postman"
//	@ResponseStatus(HttpStatus.CREATED)
//	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
//	Categoria categoriaSalva = categoriaRepository.save(categoria);	
//	publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
//	return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
//			
//	}
//	
//	@GetMapping("/{codigo}")
//	public Categoria buscarPeloCodigo(@PathVariable Long codigo) {
//		return categoriaRepository.findOne(codigo) ;
//		
//		
//	}
//	
//	/*
//	Status code:
//	2xx -> Sucesso.
//	4xx -> Erro do cliente
//	5xx -> Erro no serviço/servidor
//	
//	*/
//	
//
//}
