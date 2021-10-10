package com.santosjuliano.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.santosjuliano.cursomc.domain.Cliente;
import com.santosjuliano.cursomc.dto.ClienteDTO;
import com.santosjuliano.cursomc.repository.ClienteRepository;
import com.santosjuliano.cursomc.service.exception.DataIntegrityException;
import com.santosjuliano.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repository.save(cliente);
	}

	public Cliente update(Cliente obj) {
		Cliente objOld = find(obj.getId());
		updateData(obj, objOld);	
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida! Este registro possui relacionamento com outros registros.");
		}
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	private void updateData(Cliente obj, Cliente objOld) {
		obj.setCpfOuCnpj(objOld.getCpfOuCnpj());
		obj.setTipo(objOld.getTipo());
	}
}
