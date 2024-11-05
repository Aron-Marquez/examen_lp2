package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.demo.entity.Tipo;
import com.example.demo.service.TipoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin
public class TipoControlller {
	
	@Autowired
	private TipoService tipoService;
	
	@GetMapping
	public ResponseEntity<List<Tipo>> readAll(){
		try {
			List<Tipo> Tipos = tipoService.readAll();
			if(Tipos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Tipos, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Tipo> create(@Valid @RequestBody Tipo cat){
		try {
			Tipo c = tipoService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Tipo> getTipobyId(@PathVariable("id") Long id){
		try {
			Tipo c = tipoService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Tipo> delTipo(@PathVariable("id") Long id){
		try {
			tipoService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTipo(@PathVariable("id") Long id, @Valid @RequestBody Tipo cat){

	    Optional<Tipo> existingTipo = tipoService.read(id);
	    
	    if(existingTipo.isPresent()) {

	    	Tipo updatedTipo= existingTipo.get();
	        

	    	updatedTipo.setNombre(cat.getNombre());


	        tipoService.update(updatedTipo);
	        return new ResponseEntity<>(updatedTipo, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
