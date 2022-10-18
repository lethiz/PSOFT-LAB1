package main.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import main.dto.LoteDTO;
import main.models.Lote;
import main.models.Produto;
import main.repositories.LoteRepository;
import main.repositories.ProdutoRepository;

public class LoteService {

	private LoteRepository loteRep;
	private ProdutoRepository produtoRep;
	private Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
	
	public LoteService(LoteRepository loteRep, ProdutoRepository prodRep) {
		this.loteRep = loteRep;
		this.produtoRep = prodRep; 
	}
	
	public Collection<Lote> listaLotes() {
		return this.loteRep.getAll();
	}
	
	public String addLote(String jsonData) {
		LoteDTO loteDTO = gson.fromJson(jsonData, LoteDTO.class);
		if ( loteDTO.getQuantidade() != null && loteDTO.getQuantidade() >= 0){
			if(loteDTO.getDataFabricacao() != null){
				if(loteDTO.getIdProduto() != null && !loteDTO.getIdProduto().isEmpty() && !loteDTO.getIdProduto().isBlank()){
				String idProduto = loteDTO.getIdProduto();
				if(produtoRep.existProd(idProduto)){
					Produto produto = this.produtoRep.getProd(loteDTO.getIdProduto());
					Lote lote = new Lote(produto, loteDTO.getQuantidade(), loteDTO.getDataFabricacao());
					this.loteRep.addLote(lote);
					return lote.getId();
					} else throw new IllegalArgumentException("Parâmatro errado: produto não existe.");
				} else throw new IllegalArgumentException("Parâmatro errado: adicione o id do produto.");
			} else throw new IllegalArgumentException("Parâmatro errado: adicione data de fabricação.");
		} else throw new IllegalArgumentException("Parâmatro errado: adicione quantidade válida.");

	}

	public void deleteLote(String idLote) {
		if(this.loteRep.existLote(idLote)){
			this.loteRep.delLot(idLote);
		} else throw new IllegalArgumentException("Parâmatro errado: adicione o id do lote.");
	}

	protected void deleteLotebyProduto(String idProduto){
		if(idProduto != null && !idProduto.isEmpty() && !idProduto.isBlank()){
			ArrayList<Lote> lotes = convertCollection();
			lotes.stream().
					filter(o -> idProduto.equals(o.getProduto().getId())).
					forEach(lote -> this.loteRep.delLot(lote.getId()));
		} else throw new IllegalArgumentException("Parâmatro errado: adicione o id do produto.");
	}

	public ArrayList<Produto> searchProdutowithLote(String nomeProduto) {
		if(nomeProduto != null && !nomeProduto.isEmpty() && !nomeProduto.isBlank()){
			ArrayList<Lote> lotes = convertCollection();
			return lotes.stream().
					filter(lote ->
							(lote.getProduto().getNome().toLowerCase()).contains(nomeProduto.toLowerCase())
									&& lote.getQuantidade() > 0
					).map(Lote::getProduto).collect(Collectors.toCollection(ArrayList<Produto>::new));
		} else throw new IllegalArgumentException("Parâmatro errado: adicione o nome do produto.");

	}

	private ArrayList<Lote> convertCollection() {
		Collection<Lote> lotes = this.loteRep.getAll();
		return new ArrayList<Lote>(Arrays.asList(lotes.toArray(new Lote[0])));
	}
}