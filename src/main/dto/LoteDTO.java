package main.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoteDTO {
	
	private String idProduto;
	
	private int quantidade;

	private Date dataValidade;

	public LoteDTO(String idProduto, int quantidade, Date dataValidade) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.dataValidade = dataValidade;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

}
