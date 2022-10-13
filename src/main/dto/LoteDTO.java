package main.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		return idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

}
