package main.facades;

import main.repositories.LoteRepository;
import main.repositories.ProdutoRepository;
import main.services.LoteService;
import main.services.ProdutoService;
import main.models.Lote;
import main.models.Produto;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Essa classe conhece a classe correta para realizar uma operação requisitada no sistema.
 * A Facade é responsável por delegar as ações às classes correspondentes.
 * Observa-se apenas ações de repasse, sem implementação de lógica.
 */
public class Facade {
	
	// Repositórios
	private ProdutoRepository produtoRep;
	private LoteRepository loteRep;
	
	// Serviços
	private ProdutoService produtoService; 
	private LoteService loteService;
	
	
	public Facade() {
		this.produtoRep = new ProdutoRepository();
		this.loteRep = new LoteRepository();
		this.produtoService = new ProdutoService(loteRep, produtoRep);
		this.loteService = new LoteService(loteRep, produtoRep);
	}
	
	public Collection<Produto> listaProdutos() {
		return this.produtoService.listaProdutos();
	}
	
	public Collection<Lote> listaLotes() {
		return this.loteService.listaLotes();
	}
		
	public String criaProduto(String data) {
		return this.produtoService.addProduto(data);
	}

	public String criaLote(String data) {
		return this.loteService.addLote(data);
	}

	public void removeProduto(String idProduto) {
		this.produtoService.deleteProduto(idProduto);
		this.loteService.deleteLotebyProduto(idProduto);
	}

	public void removeLote(String idProduto, int quantidade, String dataFabricacao) {
		this.loteService.deleteLote(idProduto, quantidade, dataFabricacao);
	}

	public ArrayList<Produto> buscaProduto(String nomeProduto) {
		return this.produtoService.searchProduto(nomeProduto);
	}


	public ArrayList<Lote> buscaLote(String nomeProduto) {
		return this.loteService.searchLotebyProduto(nomeProduto);
	}
}