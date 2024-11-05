package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Tipo;

public interface TipoService {
	
	Tipo create(Tipo a);
	Tipo update(Tipo a);
	void delete(Long id);
	Optional<Tipo> read(Long id);
	List<Tipo> readAll();
}
