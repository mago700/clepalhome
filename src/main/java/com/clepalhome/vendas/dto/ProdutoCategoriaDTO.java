package com.clepalhome.vendas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ProdutoCategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=300, message = "O tamanho deve ser entre 5 e 300 caracteres")
	private String descricao;
	
	private Double preco;
	private Integer categoria;
	
	public ProdutoCategoriaDTO() {
		
	}

	public ProdutoCategoriaDTO(Integer id, String nome, String descricao, Double preco, Integer categoria) {
		super();
		this.id= id;		
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	
}
