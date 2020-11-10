package com.rodrigopinheiro.cursoUdemy.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigopinheiro.cursoUdemy.domain.Produto;
import com.rodrigopinheiro.cursoUdemy.dto.ProdutoDTO;
import com.rodrigopinheiro.cursoUdemy.resources.utils.URL;
import com.rodrigopinheiro.cursoUdemy.services.ProdutoServices;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired	
	private ProdutoServices service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "0") String nome,
			@RequestParam(value = "categorias", defaultValue = "0") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderby", defaultValue = "nome") String orderby,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		List<Integer> ids= URL.decodeIntList(categorias);
		String nomeDecoded = URL.decodeParam(nome);
		Page<Produto> lista = service.search(nomeDecoded, ids,page, linesPerPage, orderby, direction);
		Page<ProdutoDTO> listDto = lista.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}


}
