package com.rodrigopinheiro.cursoUdemy.dto;

import java.io.Serializable;

import com.rodrigopinheiro.cursoUdemy.domain.Produto;

public class ProdutoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double preco;

	public ProdutoDTO(Produto prod) {
		super();
		this.id = prod.getId();
		this.nome = prod.getNome();
		this.preco = prod.getPreco();
	}

	public ProdutoDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
