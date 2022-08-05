package com.clepalhome.vendas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clepalhome.vendas.domain.Categoria;
import com.clepalhome.vendas.domain.Cliente;
import com.clepalhome.vendas.domain.ItemPedido;
import com.clepalhome.vendas.domain.Morada;
import com.clepalhome.vendas.domain.Pagamento;
import com.clepalhome.vendas.domain.Pedido;
import com.clepalhome.vendas.domain.Produto;
import com.clepalhome.vendas.domain.enums.EstadoPagamento;
import com.clepalhome.vendas.repositories.CategoriaRepository;
import com.clepalhome.vendas.repositories.ClienteRepository;
import com.clepalhome.vendas.repositories.ItemPedidoRepository;
import com.clepalhome.vendas.repositories.MoradaRepository;
import com.clepalhome.vendas.repositories.PagamentoRepository;
import com.clepalhome.vendas.repositories.PedidoRepository;
import com.clepalhome.vendas.repositories.ProdutoRepository;

@Service
public class DBService {
	@Autowired
	private CategoriaRepository catRepo;
	
	@Autowired
	private ProdutoRepository prodRepo;
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Autowired
	private MoradaRepository morRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemRepo;
	
	
	public void instantiateTestDatabase() throws ParseException {
		
//===================================================================================================		
		Categoria roupaCama = new Categoria(null, "Jogos de Cama");
		Categoria almofada = new Categoria(null, "Almofadas");
		Categoria almofadaAplicacao = new Categoria(null, "Almofadas Com Aplicações");
//----------------------------------------------------------
		Categoria lencolBanho = new Categoria(null, "Lençol de Banho");
		Categoria toalhao = new Categoria(null, "toalhao");
		Categoria toalha = new Categoria(null, "Toalha de Banho");
		Categoria toalhete = new Categoria(null, "Toalhete de rosto");
		Categoria tapeteBanho = new Categoria(null, "Tapete de Banho");
//----------------------------------------------------------		
		Categoria jogoBrancos = new Categoria(null, "Jogo Branco Completo");
		Categoria lencol = new Categoria(null, "Lençois Coloridos");
		Categoria capa = new Categoria(null, "Capas Diversas");
		
		Produto roupaCama1 = new Produto(null, "Lençol Macio", "Um lençol muito macio", 25.50, roupaCama);
		Produto roupaCama2 = new Produto(null, "Fronha boa", "Um fronha que combina com o lençol", 10.35, roupaCama);
		Produto roupaCama3 = new Produto(null, "Cobertor Macio", "Um Cobertor macio", 38.30, roupaCama);
		
		Produto almofada1 = new Produto(null, "Almofada simples", "Almofada de uma unica cor", 15.45, almofada);
		Produto almofada2 = new Produto(null, "Almofada simples Colorida", "Almofada que pode ser em varias cores", 16.80, almofada);
		Produto almofada3 = new Produto(null, "Almofadas com Aplicação", "Almofadas que podem ser desnhadas ou bordadas", 22.65, almofadaAplicacao);
//----------------------------------------------------------
		Produto banho1 = new Produto(null, "lencolBanho", "Um lençol muito macio", 25.42, lencolBanho);
		Produto banho2 = new Produto(null, "Toalhão", "Um lençol muito macio", 32.89, toalhao);
		Produto banho3 = new Produto(null, "Toalha Macio", "Um lençol muito macio", 44.75, toalha);
		Produto banho4 = new Produto(null, "Toalhete Macia", "Um lençol muito macio", 22.48, toalhete);
		Produto banho5 = new Produto(null, "Tapete de Banhoa ", "Um lençol muito macio", 25.50, tapeteBanho);
		
		Produto branco1 = new Produto(null, "Jogo de Cama Branco", "Jogo de Branco Caro", 32.40, jogoBrancos);
		Produto lencois = new Produto(null, "Lençol Branco", "Lençol Branco Muito Caro", 28.54, lencol);
		Produto lencois1 = new Produto(null, "Lençol11 Branco", "Lençol Branco Muito Caro", 53.64, lencol);
		Produto capas = new Produto(null, "Capas Coloridas", "Capas Coloridas Muito Caro", 23.78, capa);

		roupaCama.getProdutos().addAll(Arrays.asList(roupaCama1,roupaCama2,roupaCama3));
		almofada.getProdutos().addAll(Arrays.asList(roupaCama2));
		almofadaAplicacao.getProdutos().addAll(Arrays.asList(roupaCama2, roupaCama3));
		
		lencolBanho.getProdutos().addAll(Arrays.asList(banho1, banho2));
		toalhao.getProdutos().addAll(Arrays.asList(banho2, banho3));
		toalha.getProdutos().addAll(Arrays.asList(banho3));
		toalhete.getProdutos().addAll(Arrays.asList(banho4));
		tapeteBanho.getProdutos().addAll(Arrays.asList(banho5));
		
		jogoBrancos.getProdutos().addAll(Arrays.asList(branco1, lencois));
		lencol.getProdutos().addAll(Arrays.asList(lencois, lencois1));
		capa.getProdutos().addAll(Arrays.asList(capas));
		
		
		catRepo.saveAll(Arrays.asList(roupaCama,almofada,almofadaAplicacao,lencolBanho,
				toalhao,toalha,toalhete,tapeteBanho,jogoBrancos,lencol,capa));
		prodRepo.saveAll(Arrays.asList(roupaCama1,roupaCama2,roupaCama3,almofada1,almofada2,almofada3,banho1,
				banho2,banho3,banho4,banho5,branco1,lencois,lencois1,capas));
		
		Cliente cli1 = new Cliente(null, "Celio Sato", "mago70@gmail.com");
		Cliente cli2 = new Cliente(null, "Midori Yano", "amorebf@gmail.com");
		Cliente cli3 = new Cliente(null, "Bruna Sato", "gamesdalan@gmail.com");
		Cliente cli4 = new Cliente(null, "Felipe Sato", "celiosato4@gmail.com");
		Cliente cli5 = new Cliente(null, "Igor Sato", "mago70@gmail.com");
		
		Morada mor1 = new Morada(null, "Portugal", "Rua da Agua Levada, 64", "Santa Eulalia", "4425-410", "Vizela", cli1);
		Morada mor2 = new Morada(null, "Portugal", "Travessa Giesta, 64", "Santa Eulalia", "4425-410", "Pedrouços", cli2);
		Morada mor3 = new Morada(null, "Portugal", "Ponte de Parada, 64", "Santa Eulalia", "4425-410", "Aguas Santas", cli3);
		Morada mor4 = new Morada(null, "Portugal", "Antonio Simoes, 64", "Santa Eulalia", "4425-410", "Ermesinde", cli4);
		Morada mor5 = new Morada(null, "Portugal", "Rua da  Levadinha, 64", "Santa Eulalia", "4425-410", "São Paulo", cli5);
		Morada mor6 = new Morada(null, "Portugal", "Rua de Teibas, 64", "Santa Eulalia", "4425-410", "Maua", cli1);
		Morada mor7 = new Morada(null, "Portugal", "Rua Alves Redol, 64", "Santa Eulalia", "4425-410", "Santarem", cli2);
		Morada mor8 = new Morada(null, "Portugal", "Rua Francisco Dantas, 64", "Santa Eulalia", "4425-410", "Manaus", cli3);
		
		cli1.getMorada().addAll(Arrays.asList(mor1, mor6));
		cli2.getMorada().addAll(Arrays.asList(mor2, mor7));
		cli3.getMorada().addAll(Arrays.asList(mor3, mor8));
		cli4.getMorada().addAll(Arrays.asList(mor4));
		cli5.getMorada().addAll(Arrays.asList(mor5));
		
		cli1.getTelefones().addAll(Arrays.asList("910468814", "910476688"));
		cli2.getTelefones().addAll(Arrays.asList("910468814"));
		cli3.getTelefones().addAll(Arrays.asList("910476688"));
		cli4.getTelefones().addAll(Arrays.asList("123412434", "862345327"));
		cli5.getTelefones().addAll(Arrays.asList("097876655"));
		
		cliRepository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4,cli5));
		morRepository.saveAll(Arrays.asList(mor1,mor2,mor3,mor4,mor5,mor6,mor7,mor8));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("16/07/2022 13:10"), cli1, mor6);
		Pedido ped2 = new Pedido(null, sdf.parse("14/07/2022 03:24"), cli4, mor4);
		
		Pagamento pagto1 = new Pagamento(null, EstadoPagamento.QUITADO, ped1);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new Pagamento(null, EstadoPagamento.PENDENTE, ped2);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, capas, 0.0, 1, 10.00);
		ItemPedido ip2 = new ItemPedido(ped2, banho1, 0.0, 2, 25.00);
		ItemPedido ip3 = new ItemPedido(ped1, banho3, 0.0,4, 150.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip3));
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		capas.getItens().addAll(Arrays.asList(ip1));
		banho1.getItens().addAll(Arrays.asList(ip2));
		banho3.getItens().addAll(Arrays.asList(ip3));
		
		itemRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
	
}
