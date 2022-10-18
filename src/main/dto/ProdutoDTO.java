package main.dto;

public class ProdutoDTO {
	
	private String nome; 
	
	private String fabricante;
	
	private Double preco;

	public ProdutoDTO(String nome, String fabricante, Double preco) {
		this.nome = nome;
		this.fabricante = fabricante;
		this.preco = preco;
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getFabricante() {
		return this.fabricante;
	}
	
	public Double getPreco() {
		return this.preco;
	}
}