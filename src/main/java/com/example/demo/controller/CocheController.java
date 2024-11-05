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

import com.example.demo.entity.Coche;
import com.example.demo.service.CocheService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coches")
@CrossOrigin
public class CocheController {
	
	 @Autowired
	    private CocheService cocheService;

	    @GetMapping
	    public ResponseEntity<List<Coche>> readAll() {
	        try {
	            List<Coche> coches = cocheService.readAll();
	            if (coches.isEmpty()) {
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }
	            return new ResponseEntity<>(coches, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping
	    public ResponseEntity<Coche> create(@Valid @RequestBody Coche coche) {
	        try {
	            Coche newCoche = cocheService.create(coche);
	            return new ResponseEntity<>(newCoche, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Coche> getCocheById(@PathVariable("id") Long id) {
	        try {
	            Optional<Coche> coche = cocheService.read(id);
	            return coche.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCoche(@PathVariable("id") Long id) {
	        try {
	            cocheService.delete(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Coche> updateCoche(@PathVariable("id") Long id, @Valid @RequestBody Coche coche) {
	        Optional<Coche> existingCoche = cocheService.read(id);

	        if (existingCoche.isPresent()) {
	            Coche updatedCoche = existingCoche.get();
	            updatedCoche.setPlaca(coche.getPlaca());
	            updatedCoche.setPuertas(coche.getPuertas());
	            updatedCoche.setMarca(coche.getMarca());
	            updatedCoche.setTipo(coche.getTipo());

	            cocheService.update(updatedCoche);
	            return new ResponseEntity<>(updatedCoche, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
}
