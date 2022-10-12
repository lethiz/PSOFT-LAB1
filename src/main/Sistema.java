package main;

import main.facades.Facade;

/**
 * Essa classe é configurada efetivamente com o objetivo de possibilitar o uso do usuário.
 * O Sistema é responsável por receber os comandos do usuário, para realizar operações através da fachada.
 */
public class Sistema {
	
	public static void main(String[] args) {

		String jsonP1 = "{\"nome\":\"Leite integral\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";
		
		Facade mercadoFacade = new Facade();

		// Adicionando produto ao catálogo
		String idP1 = mercadoFacade.criaProduto(jsonP1);

		String jsonL1 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":10}";;

		// Adicionando lote ao catálogo
		String idL1 = mercadoFacade.criaLote(jsonL1);

		// Lista produtos no catálogo
		System.out.println(mercadoFacade.listaProdutos());

		// Lista lotes no catálogo
		System.out.println(mercadoFacade.listaLotes());

	}
}
