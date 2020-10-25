package com.rodrigopinheiro.cursoUdemy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigopinheiro.cursoUdemy.domain.Categoria;
import com.rodrigopinheiro.cursoUdemy.repositories.CategoriaRepository;
import com.rodrigopinheiro.cursoUdemy.services.Exception.ObjectNotFoundException;

@Service
public class CategoriaServices {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
