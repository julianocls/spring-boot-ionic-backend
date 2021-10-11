package com.sbib.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbib.cursomc.domain.Cliente;
import com.sbib.cursomc.domain.Pedido;
import com.sbib.cursomc.repository.PedidoRepository;
import com.sbib.cursomc.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", tipo: " + Cliente.class.getName()));
	}

}
