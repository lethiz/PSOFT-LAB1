package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.facades.Facade;

class ProdutoTest {

	private Facade mercadoFacade = new Facade(); 
	
	private String jsonP1 = "{\"nome\":\"Leite integral\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";

	@BeforeEach
	public void createProduto() {
		mercadoFacade.criaProduto(jsonP1);
	}
	
	@Test
	public void verifyProdutosIgualUm() {
		assertEquals(1, mercadoFacade.listaProdutos().size());
	}

}