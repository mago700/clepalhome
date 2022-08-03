package com.clepalhome.vendas.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clepalhome.vendas.domain.ItemPedido;
import com.clepalhome.vendas.domain.Pedido;
import com.clepalhome.vendas.domain.enums.EstadoPagamento;
import com.clepalhome.vendas.repositories.ItemPedidoRepository;
import com.clepalhome.vendas.repositories.PagamentoRepository;
import com.clepalhome.vendas.repositories.PedidoRepository;
import com.clepalhome.vendas.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired 
	private PedidoRepository repo;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	
	@Autowired 
	private ProdutoService produtoService;
	
	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired 
	private EmailService emailService;
	
//===================================== LISTAR =============================================
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
//==================================== INSERIR =============================================
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
	
	obj = repo.save(obj);
	pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}
