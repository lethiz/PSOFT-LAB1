package main;

import main.facades.Facade;

/**
 * Essa classe é configurada efetivamente com o objetivo de possibilitar o uso do usuário.
 * O Sistema é responsável por receber os comandos do usuário, para realizar operações através da fachada.
 */
public class Sistema {
	
	public static void main(String[] args) {

		// Definindo os JSON dos produtos do catálogo
		String jsonP1 = "{\"nome\":\"Leite integral\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";

		String jsonP2 = "{\"nome\":\"Leite de Soja\", \"fabricante\":\"Parmalat\", \"preco\":15.8}";
		
		Facade mercadoFacade = new Facade();

		// Adicionando produtos ao catálogo
		String idP1 = mercadoFacade.criaProduto(jsonP1);

		String idP2 = mercadoFacade.criaProduto(jsonP2);

		// Definindo os JSON dos lotes do catálogo
		String jsonL1 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"10\", \"dataFabricacao\":12-09-2023}";

		String jsonL2 = "{\"idProduto\":\"" + idP2 + "\", \"quantidade\":\"15\", \"dataFabricacao\":15-09-2023}";

		// Adicionando lotes ao catálogo
		String idL1 = mercadoFacade.criaLote(jsonL1);

		String idL2 = mercadoFacade.criaLote(jsonL2);

		// Lista produtos no catálogo
		System.out.println(mercadoFacade.listaProdutos());

		// Lista lotes no catálogo
		System.out.println(mercadoFacade.listaLotes());

		System.out.println("Leite integral: " +mercadoFacade.buscaProduto("Leite integral"));

		System.out.println("Leite desnatado: " + mercadoFacade.buscaProduto("Leite desnatado"));

		System.out.println("Leite de Soja: " + mercadoFacade.buscaProduto("Leite de Soja"));

		System.out.println("LOTES! Leite integral: " + mercadoFacade.buscaProdutocomLote("Leite integral"));

		System.out.println("LOTES! Leite de Soja: " + mercadoFacade.buscaProdutocomLote("Leite de Soja"));

		mercadoFacade.removeProduto(idP1);

		System.out.println(mercadoFacade.listaProdutos());

		System.out.println(mercadoFacade.listaLotes());

		mercadoFacade.removeLote(idP2, 15, "15/09/2023");

		System.out.println(mercadoFacade.listaLotes());

	}
}
