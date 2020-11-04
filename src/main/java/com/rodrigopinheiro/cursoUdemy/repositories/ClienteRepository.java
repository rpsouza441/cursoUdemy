package com.rodrigopinheiro.cursoUdemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigopinheiro.cursoUdemy.domain.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{

	Cliente findByEmail(String email);

}
