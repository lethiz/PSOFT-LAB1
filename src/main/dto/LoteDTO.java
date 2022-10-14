package main.dto;

import java.util.Date;

public class LoteDTO {
	
	private String idProduto;
	
	private int quantidade;

	private Date dataFabricacao;

	public LoteDTO(String idProduto, int quantidade, Date dataFabricacao) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.dataFabricacao = dataFabricacao;
	}

	public String getIdProduto() {
		return this.idProduto;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public Date getDataFabricacao() {
		return this.dataFabricacao;
	}

}
