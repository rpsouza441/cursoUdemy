package com.rodrigopinheiro.cursoUdemy;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigopinheiro.cursoUdemy.domain.Categoria;
import com.rodrigopinheiro.cursoUdemy.domain.Cidade;
import com.rodrigopinheiro.cursoUdemy.domain.Cliente;
import com.rodrigopinheiro.cursoUdemy.domain.Endereco;
import com.rodrigopinheiro.cursoUdemy.domain.Estado;
import com.rodrigopinheiro.cursoUdemy.domain.ItemPedido;
import com.rodrigopinheiro.cursoUdemy.domain.Pagamento;
import com.rodrigopinheiro.cursoUdemy.domain.PagamentoComBoleto;
import com.rodrigopinheiro.cursoUdemy.domain.PagamentoComCartao;
import com.rodrigopinheiro.cursoUdemy.domain.Pedido;
import com.rodrigopinheiro.cursoUdemy.domain.Produto;
import com.rodrigopinheiro.cursoUdemy.domain.enums.EstadoPagamento;
import com.rodrigopinheiro.cursoUdemy.domain.enums.TipoCliente;
import com.rodrigopinheiro.cursoUdemy.repositories.CategoriaRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.CidadeRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.ClienteRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.EnderecoRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.EstadoRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.ItemPedidoRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.PagamentoRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.PedidoRepository;
import com.rodrigopinheiro.cursoUdemy.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoUdemyApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoUdemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria c1 = new Categoria(null, "informatica");
		Categoria c2 = new Categoria(null, "escritorio");
		Categoria c3 = new Categoria(null, "perfumaria");
		Categoria c4 = new Categoria(null, "cama mesa e banho");
		Categoria c5 = new Categoria(null, "eletronicos");
		Categoria c6 = new Categoria(null, "jardinagem");
		Categoria c7 = new Categoria(null, "decoracao");
		Categoria c8 = new Categoria(null, "limpeza");
		Categoria c9 = new Categoria(null, "drogaria");
		Categoria c10 = new Categoria(null, "cozinha");
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "computador", 2000.00);

		c1.getListaProdutos().addAll(Arrays.asList(p1, p2));
		c2.getListaProdutos().addAll(Arrays.asList(p2));

		p1.getListaCategorias().addAll(Arrays.asList(c1));
		p2.getListaCategorias().addAll(Arrays.asList(c1, c2));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlandia", e1);
		Cidade cidade2 = new Cidade(null, "São Paulo", e2);
		Cidade cidade3 = new Cidade(null, "Campinas", e2);
		e1.getListaCidade().addAll(Arrays.asList(cidade1));
		e2.getListaCidade().addAll(Arrays.asList(cidade2, cidade3));

		Cliente cli1 = new Cliente(null, "Maria", "maria@maria.com.br", "369874125", TipoCliente.PESSOAFISICA);
		cli1.getListaTelefone().addAll(Arrays.asList("999999999", "5555555555"));

		Endereco end1 = new Endereco(null, "Rua flores", "300", "apto 303", "Jardim", "38220832", cli1, cidade1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 1303", "Cetro", "7770832", cli1, cidade2);

		cli1.getListaEndereco().addAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 11:30"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 11:30"), cli1, end2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pgto2);

		cli1.getListaPedido().addAll(Arrays.asList(ped1, ped2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p2, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getListaItem().addAll(Arrays.asList(ip1, ip2));
		ped2.getListaItem().addAll(Arrays.asList(ip3));

		p1.getListaItem().addAll(Arrays.asList(ip1));
		p2.getListaItem().addAll(Arrays.asList(ip2, ip3));

		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
		produtoRepository.saveAll(Arrays.asList(p1, p2));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		clienteRepository.saveAll(Arrays.asList(cli1));

		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
