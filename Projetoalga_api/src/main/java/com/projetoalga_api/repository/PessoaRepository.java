package com.projetoalga_api.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoalga_api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {



}




//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.projetoalga_api.model.Pessoa; //ATENÇÃO
//
//public interface PessoaRepository extends JpaRepository <Pessoa, Long >{
//
//	
//
//}
