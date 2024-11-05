package com.example.demo.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.MarcaDao;
import com.example.demo.entity.Marca;
import com.example.demo.repository.MarcaRepository;

@Component
public class MarcaDaoImpl implements MarcaDao {
	
	@Autowired
	private MarcaRepository repository;
	
	@Override
	public Marca create(Marca a) {
		// TODO Auto-generated method stub
		return repository.save(a);
	}

	@Override
	public Marca update(Marca a) {
		// TODO Auto-generated method stub
		return repository.save(a);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Optional<Marca> read(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public List<Marca> readAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
