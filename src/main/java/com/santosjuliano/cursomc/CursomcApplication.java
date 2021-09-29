package com.santosjuliano.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.santosjuliano.cursomc.domain.Categoria;
import com.santosjuliano.cursomc.domain.Cidade;
import com.santosjuliano.cursomc.domain.Cliente;
import com.santosjuliano.cursomc.domain.Endereco;
import com.santosjuliano.cursomc.domain.Estado;
import com.santosjuliano.cursomc.domain.Produto;
import com.santosjuliano.cursomc.domain.enums.TipoCliente;
import com.santosjuliano.cursomc.repository.CategoriaRepository;
import com.santosjuliano.cursomc.repository.CidadeRepository;
import com.santosjuliano.cursomc.repository.ClienteRepository;
import com.santosjuliano.cursomc.repository.EnderecoRepository;
import com.santosjuliano.cursomc.repository.EstadoRepository;
import com.santosjuliano.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));

		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Juliano", "juliano@juliano.com", "12345678901", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("34991248232", "34991234532"));

		Endereco ed1 = new Endereco(null, "Rua xpto", "777", "Endereço da lua", "Qualquer", "38500123", cli1, cid1);
		Endereco ed2 = new Endereco(null, "Rua abc", "444", "Endereço da terra", "Central", "38400123", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(ed1, ed2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(ed1, ed2));

	}

}
