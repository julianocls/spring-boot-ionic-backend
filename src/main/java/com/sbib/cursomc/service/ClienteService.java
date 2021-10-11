package com.sbib.cursomc.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sbib.cursomc.domain.Cidade;
import com.sbib.cursomc.domain.Cliente;
import com.sbib.cursomc.domain.Endereco;
import com.sbib.cursomc.domain.enums.TipoCliente;
import com.sbib.cursomc.dto.ClienteDTO;
import com.sbib.cursomc.dto.ClienteNewDTO;
import com.sbib.cursomc.repository.ClienteRepository;
import com.sbib.cursomc.repository.EnderecoRepository;
import com.sbib.cursomc.service.exception.DataIntegrityException;
import com.sbib.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;
	@Autowired
	EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
			throw new DataIntegrityException("Exclusão não permitida! Este cliente possui relacionamento com pedidos.");
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


	public Cliente fromDto(ClienteNewDTO objNewDTO) {
		Cliente cli = new Cliente(null, objNewDTO.getNome(), objNewDTO.getEmail(), objNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(objNewDTO.getTipo()));
		Cidade cid = new Cidade(objNewDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objNewDTO.getLogradouro(), objNewDTO.getNumero(), objNewDTO.getComplemento(), objNewDTO.getBairro(), objNewDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objNewDTO.getTelefone1());
		if(objNewDTO.getTelefone2() != null) {
			cli.getTelefones().add(objNewDTO.getTelefone2());			
		}
		if(objNewDTO.getTelefone3() != null) {
			cli.getTelefones().add(objNewDTO.getTelefone3());			
		}
		return cli;
	}
	
	private void updateData(Cliente obj, Cliente objOld) {
		obj.setCpfOuCnpj(objOld.getCpfOuCnpj());
		obj.setTipo(objOld.getTipo());
	}
}
