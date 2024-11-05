package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CocheDao;
import com.example.demo.entity.Coche;
import com.example.demo.service.CocheService;
@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheDao dao;
	
	@Override
	public Coche create(Coche a) {
		// TODO Auto-generated method stub
		return dao.create(a);
	}

	@Override
	public Coche update(Coche a) {
		// TODO Auto-generated method stub
		return dao.update(a);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Optional<Coche> read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Coche> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}
