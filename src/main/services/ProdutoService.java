package main.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

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
		ProdutoDTO prodDTO = gson.fromJson(jsonData, ProdutoDTO.class);
		Produto produto = new Produto(prodDTO.getNome(), prodDTO.getFabricante(), prodDTO.getPreco());
		
		this.prodRep.addProduto(produto);
		
		return produto.getId();
	}

	public void deleteProduto(String idProduto) {
		this.prodRep.delProd(idProduto);
	}

    public ArrayList<Produto> searchProduto(String nomeProduto) {
		ArrayList<Produto> produtos = convertCollection();
		return produtos.stream().filter(o -> nomeProduto.equals(o.getNome())).collect(Collectors.toCollection(ArrayList<Produto>::new));
    }

	private ArrayList<Produto> convertCollection() {
		Collection<Produto> produtos = this.prodRep.getAll();
		return new ArrayList<Produto>(Arrays.asList(produtos.toArray(new Produto[0])));
	}
}