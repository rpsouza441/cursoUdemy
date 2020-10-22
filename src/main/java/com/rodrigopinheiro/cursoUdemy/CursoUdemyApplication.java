package com.rodrigopinheiro.cursoUdemy;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigopinheiro.cursoUdemy.domain.Categoria;
import com.rodrigopinheiro.cursoUdemy.domain.Produto;
import com.rodrigopinheiro.cursoUdemy.repositories.CategoriaRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoUdemyApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoUdemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria c1 = new Categoria(null, "informatica");
		Categoria c2 = new Categoria(null, "escritorio");
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "computador", 2000.00);

		c1.getListaProdutos().addAll(Arrays.asList(p1, p2));
		c2.getListaProdutos().addAll(Arrays.asList(p2));

		p1.getListaCategorias().addAll(Arrays.asList(c1));
		p2.getListaCategorias().addAll(Arrays.asList(c1, c2));

		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2));

	}

}
