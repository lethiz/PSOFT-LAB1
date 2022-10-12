package test;

import static org.junit.jupiter.api.Assertions.*;

import main.models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.facades.Facade;

import java.util.ArrayList;

class ProdutoTest {

	private Facade mercadoFacade = new Facade();

	private String jsonP1 = "{\"nome\":\"Leite integral\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";
	private String idP1;

	@BeforeEach
	public void createProduto() {
		this.idP1 = mercadoFacade.criaProduto(jsonP1);
	}
	
	@Test
	public void verifyProdutoAdicionado() {
		assertEquals(1, mercadoFacade.listaProdutos().size());
	}

	@Test
	public void verifyProdutoDeletado() {
		mercadoFacade.removeProduto(idP1);
		assertEquals(0, mercadoFacade.listaProdutos().size());
	}

	@Test
	public void searchProduto() {
		ArrayList<Produto> targets = mercadoFacade.buscaProduto("Leite integral");
		assertEquals(1, targets.size());
		targets = mercadoFacade.buscaProduto("Leite desnatado");
		assertEquals(0, targets.size());
	}

}
