package main.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import main.models.Lote;
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
		ProdutoDTO produtoDTO = gson.fromJson(jsonData, ProdutoDTO.class);
		if (produtoDTO.getNome() != null && !produtoDTO.getNome().isBlank() && !produtoDTO.getNome().isEmpty()){
			if(produtoDTO.getFabricante() != null && !produtoDTO.getFabricante().isBlank() && !produtoDTO.getFabricante().isEmpty()){
				if(produtoDTO.getPreco() != null && produtoDTO.getPreco() >= 0){
					Produto produto = new Produto(produtoDTO.getNome(), produtoDTO.getFabricante(), produtoDTO.getPreco());
					this.produtoRepository.addProduto(produto);
					return produto.getId();
				} else throw new IllegalArgumentException("Parâmatro errado: adicione um preço válido.");
			} else throw new IllegalArgumentException("Parâmatro errado: adicione um fabricante válido.");
		} else throw new IllegalArgumentException("Parâmatro errado: adicione um nome válido.");
	}

	public void deleteProduto(String idProduto) {
		if (this.produtoRepository.existProd(idProduto)){
			this.produtoRepository.delProd(idProduto);
			this.loteService.deleteLotebyProduto(idProduto);
		} else throw new IllegalArgumentException("Parâmatro errado: adicione o id do produto.");
	}

    public ArrayList<Produto> searchProduto(String nomeProduto) {
		if(nomeProduto != null && !nomeProduto.isEmpty() && !nomeProduto.isBlank()){
			ArrayList<Produto> listProdutos = convertCollection();
			return listProdutos.stream().
					filter(produto -> (produto.getNome().toLowerCase()).contains(nomeProduto.toLowerCase())).
					collect(Collectors.toCollection(ArrayList<Produto>::new));
		} else throw new IllegalArgumentException("Parâmatro errado: adicione o nome do produto.");
    }

	private ArrayList<Produto> convertCollection() {
		Collection<Produto> produtos = this.produtoRepository.getAll();
		return new ArrayList<Produto>(Arrays.asList(produtos.toArray(new Produto[0])));
	}
}