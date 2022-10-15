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
	
	private ProdutoRepository produtoRepository;

	private LoteService loteService;
	private Gson gson = new Gson();
	
	public ProdutoService(LoteService loteService, ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
		this.loteService = loteService;
	}
	
	public Collection<Produto> listaProdutos() {
		return this.produtoRepository.getAll();
	}
	
	public String addProduto(String jsonData) {
		ProdutoDTO prodDTO = gson.fromJson(jsonData, ProdutoDTO.class);
		Produto produto = new Produto(prodDTO.getNome(), prodDTO.getFabricante(), prodDTO.getPreco());
		
		this.produtoRepository.addProduto(produto);
		
		return produto.getId();
	}

	public void deleteProduto(String idProduto) {
		this.produtoRepository.delProd(idProduto);
		this.loteService.deleteLotebyProduto(idProduto);
	}

    public ArrayList<Produto> searchProduto(String nomeProduto) {
		ArrayList<Produto> produtos = convertCollection();
		return produtos.stream().
				filter(o -> (nomeProduto.toLowerCase()).equals(o.getNome().toLowerCase())).
				collect(Collectors.toCollection(ArrayList<Produto>::new));
    }

	private ArrayList<Produto> convertCollection() {
		Collection<Produto> produtos = this.produtoRepository.getAll();
		return new ArrayList<Produto>(Arrays.asList(produtos.toArray(new Produto[0])));
	}
}