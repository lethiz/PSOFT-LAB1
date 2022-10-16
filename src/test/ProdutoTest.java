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
	public void verifyProdutoDeletadoExiste() {
		mercadoFacade.removeProduto(idP1);
		assertEquals(0, mercadoFacade.listaProdutos().size());
	}

	@Test
	public void verifyProdutoDeletadoNaoExiste() {
		mercadoFacade.removeProduto("id de produto que não existe");
		assertEquals(1, mercadoFacade.listaProdutos().size());
	}

	@Test
	public void verifyProdutoDeletadoExisteComLote() {
		String jsonL1 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"10\", \"dataFabricacao\":12-09-2023}";
		mercadoFacade.criaLote(jsonL1);
		assertEquals(1, mercadoFacade.listaProdutos().size());
		assertEquals(1, mercadoFacade.listaLotes().size());
		mercadoFacade.removeProduto(idP1);
		assertEquals(0, mercadoFacade.listaProdutos().size());
		assertEquals(0, mercadoFacade.listaLotes().size());
	}

	@Test
	public void verifyProdutoDeletadoExisteComLoteRepetido() {
		String jsonL1 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"10\", \"dataFabricacao\":12-09-2023}";
		mercadoFacade.criaLote(jsonL1);
		mercadoFacade.criaLote(jsonL1);
		assertEquals(1, mercadoFacade.listaProdutos().size());
		assertEquals(2, mercadoFacade.listaLotes().size());
		mercadoFacade.removeProduto(idP1);
		assertEquals(0, mercadoFacade.listaProdutos().size());
		assertEquals(0, mercadoFacade.listaLotes().size());
	}

	@Test
	public void searchProduto() {
		mercadoFacade.criaProduto(jsonP1);
		ArrayList<Produto> targets = mercadoFacade.buscaProduto("Leite integral");
		String nomeProduto = targets.get(0).getNome();
		assertEquals(2, targets.size());
		assertEquals(nomeProduto, "Leite integral");
		targets = mercadoFacade.buscaProduto("Leite desnatado");
		assertEquals(0, targets.size());
	}

	@Test
	public void searchProdutoVariacao() {
		ArrayList<Produto> targets = mercadoFacade.buscaProduto("leitE inTegral");
		String nomeProduto = targets.get(0).getNome();
		assertEquals(1, targets.size());
		assertNotEquals(nomeProduto, "leitE inTegral");
		targets = mercadoFacade.buscaProduto("leitE desNatado");
		assertEquals(0, targets.size());
	}

	@Test
	public void searchProdutoRepeticao() {
		mercadoFacade.criaProduto(jsonP1);
		ArrayList<Produto> targets = mercadoFacade.buscaProduto("Leite integral");
		assertEquals(2, targets.size());
		assertEquals(targets.get(0).getNome(), "Leite integral");
		assertEquals(targets.get(1).getNome(), "Leite integral");
	}

}
