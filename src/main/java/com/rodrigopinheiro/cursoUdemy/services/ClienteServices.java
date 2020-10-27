package com.rodrigopinheiro.cursoUdemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigopinheiro.cursoUdemy.domain.Cliente;
import com.rodrigopinheiro.cursoUdemy.dto.ClienteDTO;
import com.rodrigopinheiro.cursoUdemy.repositories.ClienteRepository;
import com.rodrigopinheiro.cursoUdemy.services.Exception.DataIntegrityException;
import com.rodrigopinheiro.cursoUdemy.services.Exception.ObjectNotFoundException;

@Service
public class ClienteServices {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
		return repo.findAll(pageRequest);

	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newobj = find(obj.getId());
		updateData(newobj, obj);
		return repo.save(newobj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}

	}

	public Cliente fromDTO(ClienteDTO objDOT) {
		return new Cliente(objDOT.getId(), objDOT.getNome(), objDOT.getEmail(), null, null);
	}

	private void updateData(Cliente newobj, Cliente obj) {
		newobj.setNome(obj.getNome());
		newobj.setEmail(obj.getEmail());
	}

}
