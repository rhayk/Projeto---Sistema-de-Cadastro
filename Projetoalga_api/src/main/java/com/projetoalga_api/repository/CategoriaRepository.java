package com.projetoalga_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoalga_api.model.Categoria;

public interface  CategoriaRepository extends JpaRepository <Categoria, Long> {

	
	
}
