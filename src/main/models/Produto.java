package main.models;
import java.util.Objects;
import java.util.UUID;

public class Produto {
	
	private String id;
	
	private String nome; 
	
	private String fabricante;
	
	private Double preco;

	public Produto(String nome, String fabricante, Double preco) {
		this.id = UUID.randomUUID().toString();
		this.nome = nome;
		this.fabricante = fabricante;
		this.preco = preco;
	}
		
	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getFabricante() {
		return fabricante;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public String toString() {
		return "Produto: " + getNome() + " - Fabricante: " + getFabricante();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Produto produto = (Produto) o;
		return Objects.equals(id, produto.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
