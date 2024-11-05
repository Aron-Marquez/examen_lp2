package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.entity.Coche;


public interface CocheService {
	
	Coche create(Coche a);
	Coche update(Coche a);
	void delete(Long id);
	Optional<Coche> read(Long id);
	List<Coche> readAll();
}
