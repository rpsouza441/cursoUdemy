package com.rodrigopinheiro.cursoUdemy.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigopinheiro.cursoUdemy.domain.Cidade;
import com.rodrigopinheiro.cursoUdemy.domain.Cliente;
import com.rodrigopinheiro.cursoUdemy.domain.Endereco;
import com.rodrigopinheiro.cursoUdemy.domain.enums.TipoCliente;
import com.rodrigopinheiro.cursoUdemy.dto.ClienteDTO;
import com.rodrigopinheiro.cursoUdemy.dto.ClienteNewDTO;
import com.rodrigopinheiro.cursoUdemy.repositories.ClienteRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.EnderecoRepository;
import com.rodrigopinheiro.cursoUdemy.services.Exception.DataIntegrityException;
import com.rodrigopinheiro.cursoUdemy.services.Exception.ObjectNotFoundException;

@Service
public class ClienteServices {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

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

	/* Para garantir que o endereço seja salvo junto com o cliente */
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getListaEndereco());
		return obj;
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

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));

		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);

		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);

		cli.getListaEndereco().add(end);

		cli.getListaTelefone().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getListaTelefone().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getListaTelefone().add(objDto.getTelefone3());
		}
		return cli;
	}

}
