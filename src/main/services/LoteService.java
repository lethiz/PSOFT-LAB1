package main.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import main.dto.LoteDTO;
import main.models.Lote;
import main.models.Produto;
import main.repositories.LoteRepository;
import main.repositories.ProdutoRepository;

public class LoteService {

	private LoteRepository loteRep;
	private ProdutoRepository produtoRep;
	private Gson gson = new Gson();
	
	public LoteService(LoteRepository loteRep, ProdutoRepository prodRep) {
		this.loteRep = loteRep;
		this.produtoRep = prodRep; 
	}
	
	public Collection<Lote> listaLotes() {
		return this.loteRep.getAll();
	}
	
	public String addLote(String jsonData) {
		LoteDTO loteDTO = gson.fromJson(jsonData, LoteDTO.class);
		Produto prod = this.produtoRep.getProd(loteDTO.getIdProduto());
		
		Lote lote = new Lote(prod, loteDTO.getQuantidade());
		this.loteRep.addLote(lote);

		return lote.getId();
	}

	public void deleteLote(String idProduto){
		ArrayList<Lote> lotes = convertCollection();
		ArrayList<Lote> targetLotes = lotes.stream().filter(o -> idProduto.equals(o.getProduto().getId())).collect(Collectors.toCollection(ArrayList<Lote>::new));
		targetLotes.forEach(lote -> this.loteRep.delLot(lote.getId()));
	}

	private ArrayList<Lote> convertCollection() {
		Collection<Lote> produtos = this.loteRep.getAll();
		return new ArrayList<Lote>(Arrays.asList(produtos.toArray(new Lote[0])));
	}
}