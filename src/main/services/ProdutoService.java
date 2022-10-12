package main.services;

import java.util.Collection;

import com.google.gson.Gson;

import main.models.Produto;
import main.repositories.LoteRepository;
import main.repositories.ProdutoRepository;
import main.dto.ProdutoDTO;

public class ProdutoService {
	
	private ProdutoRepository prodRep;
	private Gson gson = new Gson();
	
	public ProdutoService(LoteRepository loteRep, ProdutoRepository prodRep) {
		this.prodRep = prodRep;
	}
	
	public Collection<Produto> listaProdutos() {
		return this.prodRep.getAll();
	}
	
	public String addProduto(String jsonData) {
		ProdutoDTO prodDTO= gson.fromJson(jsonData, ProdutoDTO.class);
		Produto produto = new Produto(prodDTO.getNome(), prodDTO.getFabricante(), prodDTO.getPreco());
		
		this.prodRep.addProduto(produto);
		
		return produto.getId();
	}
}