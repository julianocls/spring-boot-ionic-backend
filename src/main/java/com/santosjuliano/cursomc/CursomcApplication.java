package com.santosjuliano.cursomc;

import java.text.SimpleDateFormat;
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
import com.santosjuliano.cursomc.domain.ItemPedido;
import com.santosjuliano.cursomc.domain.Pagamento;
import com.santosjuliano.cursomc.domain.PagamentoComBoleto;
import com.santosjuliano.cursomc.domain.PagamentoComCartao;
import com.santosjuliano.cursomc.domain.Pedido;
import com.santosjuliano.cursomc.domain.Produto;
import com.santosjuliano.cursomc.domain.enums.EstadoPagamento;
import com.santosjuliano.cursomc.domain.enums.TipoCliente;
import com.santosjuliano.cursomc.repository.CategoriaRepository;
import com.santosjuliano.cursomc.repository.CidadeRepository;
import com.santosjuliano.cursomc.repository.ClienteRepository;
import com.santosjuliano.cursomc.repository.EnderecoRepository;
import com.santosjuliano.cursomc.repository.EstadoRepository;
import com.santosjuliano.cursomc.repository.ItemPedidoRepository;
import com.santosjuliano.cursomc.repository.PagamentoRepository;
import com.santosjuliano.cursomc.repository.PedidoRepository;
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
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Games & Consoles");
		Categoria cat5 = new Categoria(null, "Saúde");
		Categoria cat6 = new Categoria(null, "Esporte & Lazer");
		Categoria cat7 = new Categoria(null, "Audio & TV");
		Categoria cat8 = new Categoria(null, "Vestuários");
		Categoria cat9 = new Categoria(null, "Calçados");
		Categoria cat10 = new Categoria(null, "Fitness");
		Categoria cat11 = new Categoria(null, "Auto");
		Categoria cat12 = new Categoria(null, "Perfumaria");
		Categoria cat13 = new Categoria(null, "Eletro");
		Categoria cat14 = new Categoria(null, "Eletrônicos");
		Categoria cat15 = new Categoria(null, "Smartphones");
		Categoria cat16 = new Categoria(null, "Camping");
		Categoria cat17 = new Categoria(null, "Móveis");
		Categoria cat18 = new Categoria(null, "Portáteis");
		Categoria cat19 = new Categoria(null, "Importados");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12, cat13, cat14, cat15, cat16, cat17, cat18, cat19));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.setPedidos(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
