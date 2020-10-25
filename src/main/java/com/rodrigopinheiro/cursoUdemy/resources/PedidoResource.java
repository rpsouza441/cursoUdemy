package com.rodrigopinheiro.cursoUdemy.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigopinheiro.cursoUdemy.domain.Pedido;
import com.rodrigopinheiro.cursoUdemy.services.PedidoServices;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoServices service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = service.buscar(id);

		return ResponseEntity.ok().body(obj);
	}

}
