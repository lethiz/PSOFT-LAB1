package main.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import main.dto.LoteDTO;
import main.models.Lote;
import main.models.Produto;
import main.repositories.LoteRepository;
import main.repositories.ProdutoRepository;

public class LoteService {

	private LoteRepository loteRepository;
	private ProdutoRepository produtoRepository;
	private Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
	
	public LoteService(LoteRepository loteRepository, ProdutoRepository produtoRepository) {
		this.loteRepository = loteRepository;
		this.produtoRepository = produtoRepository;
	}
	
	public Collection<Lote> listaLotes() {
		return this.loteRepository.getAll();
	}
	
	public String addLote(String jsonData) {
		LoteDTO loteDTO = gson.fromJson(jsonData, LoteDTO.class);
		if ( loteDTO.getQuantidade() != null && loteDTO.getQuantidade() >= 1){
			if(loteDTO.getDataFabricacao() != null){
				if(loteDTO.getIdProduto() != null && !loteDTO.getIdProduto().isEmpty() && !loteDTO.getIdProduto().isBlank()){
				String idProduto = loteDTO.getIdProduto();
				if(produtoRepository.existProd(idProduto)){
					Produto produto = this.produtoRepository.getProd(loteDTO.getIdProduto());
					Lote lote = new Lote(produto, loteDTO.getQuantidade(), loteDTO.getDataFabricacao());
					this.loteRepository.addLote(lote);
					return lote.getId();
					} else throw new IllegalArgumentException("Parâmatro errado: produto não existe.");
				} else throw new IllegalArgumentException("Parâmatro errado: adicione o id do produto.");
			} else throw new IllegalArgumentException("Parâmatro errado: adicione data de fabricação.");
		} else throw new IllegalArgumentException("Parâmatro errado: adicione quantidade válida.");

	}

	public void deleteLote(String idLote) {
		if(this.loteRepository.existLote(idLote)){
			this.loteRepository.delLot(idLote);
		} else throw new IllegalArgumentException("Parâmatro errado: adicione o id do lote.");
	}

	protected void deleteLotebyProduto(String idProduto){
		if(idProduto != null && !idProduto.isEmpty() && !idProduto.isBlank()){
			ArrayList<Lote> lotes = convertCollection();
			lotes.stream().
					filter(o -> idProduto.equals(o.getProduto().getId())).
					forEach(lote -> this.loteRepository.delLot(lote.getId()));
		} else throw new IllegalArgumentException("Parâmatro errado: adicione o id do produto.");
	}

	public ArrayList<Produto> searchProdutowithLote(String nomeProduto) {
		if(nomeProduto != null && !nomeProduto.isEmpty() && !nomeProduto.isBlank()){
			ArrayList<Lote> listaLotes = convertCollection();
			return listaLotes.stream().
					filter(lote ->
							(lote.getProduto().getNome().toLowerCase()).contains(nomeProduto.toLowerCase())
									&& lote.getQuantidade() > 0
					).map(Lote::getProduto).distinct().collect(Collectors.toCollection(ArrayList<Produto>::new));
		} else throw new IllegalArgumentException("Parâmatro errado: adicione o nome do produto.");
	}

	private ArrayList<Lote> convertCollection() {
		Collection<Lote> lotes = this.loteRepository.getAll();
		return new ArrayList<Lote>(Arrays.asList(lotes.toArray(new Lote[0])));
	}
}