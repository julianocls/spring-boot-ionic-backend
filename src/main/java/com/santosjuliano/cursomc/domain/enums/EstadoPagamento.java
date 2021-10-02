package com.santosjuliano.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), 
	QUITADO(2, "Quitado"), 
	CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String descricao;

	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (EstadoPagamento pg : EstadoPagamento.values()) {
			if (pg.getCodigo().equals(codigo)) {
				return pg;
			}
		}

		throw new IllegalArgumentException("ID: " + codigo + " nao encontrado!");
	}

}
