package com.santosjuliano.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santosjuliano.cursomc.domain.Cliente;
import com.santosjuliano.cursomc.domain.Pedido;
import com.santosjuliano.cursomc.repository.PedidoRepository;
import com.santosjuliano.cursomc.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", tipo: " + Cliente.class.getName()));
	}

}
