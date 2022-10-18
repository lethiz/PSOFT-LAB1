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
	public void createProdutoNomeNull() {
		String jsonP2 = "{\"fabricante\":\"Parmalat\", \"preco\":10.5}";
		try{
			mercadoFacade.criaProduto(jsonP2);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void createProdutoNomeVazio() {
		String jsonP2 = "{\"nome\":\"     \", \"fabricante\":\"Parmalat\", \"preco\":10.5}";
		try{
			mercadoFacade.criaProduto(jsonP2);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void createProdutoNomeBranco() {
		String jsonP2 = "{\"nome\":\"\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";
		try{
			mercadoFacade.criaProduto(jsonP2);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void createProdutoFabricanteNull() {
		String jsonP2 = "{\"fabricante\":\"Parmalat\", \"preco\":10.5}";
		try{
			mercadoFacade.criaProduto(jsonP2);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void createProdutoFabricanteVazio() {
		String jsonP2 = "{\"nome\":\"\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";
		try{
			mercadoFacade.criaProduto(jsonP2);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void createProdutoFabricanteBranco() {
		String jsonP2 = "{\"nome\":\"    \", \" \":\"Parmalat\", \"preco\":10.5}";
		try{
			mercadoFacade.criaProduto(jsonP2);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void createProdutoPrecoNull() {
		String jsonP2 = "{\"nome\":\"Leite integral\", \"fabricante\":\"Parmalat\"}";
		try{
			mercadoFacade.criaProduto(jsonP2);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void createProdutoPrecoNegativo() {
		String jsonP2 = "{\"nome\":\"Leite integral\", \"fabricante\":\"Parmalat\", \"preco\":-10.5}";
		try{
			mercadoFacade.criaProduto(jsonP2);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void deleteProdutoIdNull() {
		try{
			mercadoFacade.removeProduto(null);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void deleteProdutoNaoExiste() {
		try{
			mercadoFacade.removeProduto("ABCDE");
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void searchProdutoNomeNull() {
		try{
			mercadoFacade.buscaProduto(null);
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void searchProdutoNomeVazio() {
		try{
			mercadoFacade.buscaProduto("");
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void searchProdutoNomeBranco() {
		try{
			mercadoFacade.buscaProduto("    ");
		} catch(Exception error){
			error.printStackTrace();
		}
	}

	@Test
	public void verifyProdutoDeletadoExiste() {
		mercadoFacade.removeProduto(idP1);
		assertEquals(0, mercadoFacade.listaProdutos().size());
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
		String jsonP2 = "{\"nome\":\"Leite de Soja\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";
		mercadoFacade.criaProduto(jsonP2);
		ArrayList<Produto> targets = mercadoFacade.buscaProduto("Leite integral");
		assertEquals(2, targets.size());
		assertEquals(targets.get(0).getNome(), "Leite integral");
		assertEquals(targets.get(1).getNome(), "Leite integral");
	}

	@Test
	public void searchProdutoVariados() {
		mercadoFacade.criaProduto(jsonP1);
		String jsonP2 = "{\"nome\":\"Leite de Soja\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";
		mercadoFacade.criaProduto(jsonP2);
		ArrayList<Produto> targets = mercadoFacade.buscaProduto("LeiTe");
		assertEquals(3, targets.size());
		assertTrue(targets.get(0).getNome().toLowerCase().contains("leite"));
		assertTrue(targets.get(1).getNome().toLowerCase().contains("leite"));
		assertTrue(targets.get(2).getNome().toLowerCase().contains("leite"));
	}

}
