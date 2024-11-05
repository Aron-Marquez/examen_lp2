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

import com.example.demo.entity.Marca;
import com.example.demo.service.MarcaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping
	public ResponseEntity<List<Marca>> readAll(){
		try {
			List<Marca> Marcas = marcaService.readAll();
			if(Marcas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Marcas, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Marca> create(@Valid @RequestBody Marca cat){
		try {
			Marca c = marcaService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Marca> getMarcabyId(@PathVariable("id") Long id){
		try {
			Marca c = marcaService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Marca> delMarca(@PathVariable("id") Long id){
		try {
			marcaService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMarca(@PathVariable("id") Long id, @Valid @RequestBody Marca cat){

	    Optional<Marca> existingMarca = marcaService.read(id);
	    
	    if(existingMarca.isPresent()) {

	    	Marca updatedMarca= existingMarca.get();
	        

	    	updatedMarca.setNombre(cat.getNombre());


	        marcaService.update(updatedMarca);
	        return new ResponseEntity<>(updatedMarca, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
