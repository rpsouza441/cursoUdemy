package com.rodrigopinheiro.cursoUdemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigopinheiro.cursoUdemy.domain.Produto;

@Repository()
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
