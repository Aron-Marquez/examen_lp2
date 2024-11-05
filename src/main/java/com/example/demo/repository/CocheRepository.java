package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long> {

}
