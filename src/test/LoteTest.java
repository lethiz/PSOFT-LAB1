package test;

import static org.junit.jupiter.api.Assertions.*;

import main.models.Lote;
import main.models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.facades.Facade;

import java.util.ArrayList;

class LoteTest {

    private Facade mercadoFacade = new Facade();

    private String jsonP1 = "{\"nome\":\"Leite integral\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";
    private String idP1;
    private String idL1;

    @BeforeEach
    public void createLote() {
        this.idP1 = mercadoFacade.criaProduto(jsonP1);
        String jsonL1 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"10\", \"dataFabricacao\":12-09-2023}";
        this.idL1 = mercadoFacade.criaLote(jsonL1);
    }

    @Test
    public void verifyLoteAdicionado() {
        assertEquals(1, mercadoFacade.listaLotes().size());
    }

    @Test
    public void createLoteSemProduto() {
        String jsonL2 = "{\"quantidade\":\"10\", \"dataFabricacao\":12-09-2023}";
        try{
            mercadoFacade.criaLote(jsonL2);

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void createLoteProdutoNaoExiste() {
        String jsonL2 =  "{\"idProduto\":\"ABCDE\", \"quantidade\":\"10\", \"dataFabricacao\":12-09-2023}";
        try{
            mercadoFacade.criaLote(jsonL2);

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void createLoteSemQuantidade() {
        String jsonL2 = "{\"idProduto\":\"" + idP1 + "\", \"dataFabricacao\":12-09-2023}";
        try{
            mercadoFacade.criaLote(jsonL2);

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void createLoteQuantidadeNula() {
        String jsonL2 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"0\", \"dataFabricacao\":12-09-2023}";
        try{
            mercadoFacade.criaLote(jsonL2);

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void createLoteQuantidadeNegativa() {
        String jsonL2 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"-1\", \"dataFabricacao\":12-09-2023}";
        try{
            mercadoFacade.criaLote(jsonL2);

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void createLoteSemDatadeValidade() {
        String jsonL2 ="{\"idProduto\":\"" + idP1 + "\", \"quantidade\": 10}";
        try{
            mercadoFacade.criaLote(jsonL2);

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void createLoteDatadeValidadeErrada() {
        String jsonL2 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"10\", \"dataFabricacao\":ABCDE}";
        try{
            mercadoFacade.criaLote(jsonL2);

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void createLoteDatadeValidadeVazia() {
        String jsonL2 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"10\", \"dataFabricacao\": }";
        try{
            mercadoFacade.criaLote(jsonL2);

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void deleteLoteNaoExiste() {
        try{
            mercadoFacade.removeLote("ABCDE");

        } catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    public void deleteLoteIdNull() {
        try{
            mercadoFacade.removeLote(null);

        } catch(Exception error){
            error.printStackTrace();
        }
    }


    @Test
    public void verifyLoteDados() {
        assertEquals(1, mercadoFacade.listaLotes().size());
        Lote lotezinho = mercadoFacade.listaLotes().toArray(new Lote[0])[0];
        Produto produtinho = mercadoFacade.buscaProduto("Leite integral").get(0);
        assertEquals(lotezinho.getQuantidade(),  10);
        assertEquals(lotezinho.getProduto(),  produtinho);
        assertEquals(lotezinho.getDataFabricacaoString(),  "12/09/2023");
    }
    @Test
    public void verifyProdutoDeletado() {
        assertEquals(1, mercadoFacade.listaLotes().size());
        mercadoFacade.removeProduto(idP1);
        assertEquals(0, mercadoFacade.listaLotes().size());
    }

    @Test
    public void verifyLoteDeletado() {
        String jsonL1 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"10\", \"dataFabricacao\":12-09-2023}";
        String idL2 = mercadoFacade.criaLote(jsonL1);
        assertEquals(2, mercadoFacade.listaLotes().size());
        mercadoFacade.removeLote(idL1);
        assertEquals(1, mercadoFacade.listaLotes().size());
    }

    @Test
    public void searchLote() {
        ArrayList<Produto> targets = mercadoFacade.buscaProdutocomLote("Leite integral");
        assertEquals(1, targets.size());
        targets = mercadoFacade.buscaProdutocomLote("Leite desnatado");
        assertEquals(0, targets.size());
    }

    @Test
    public void searchLoteVariacao() {
        ArrayList<Produto> targets = mercadoFacade.buscaProdutocomLote("leiTe inTegral");
        assertEquals(1, targets.size());
        targets = mercadoFacade.buscaProdutocomLote("leiTe desNatado");
        assertEquals(0, targets.size());
    }

    @Test
    public void searchLoteRepeticao() {
        String jsonL1 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":\"10\", \"dataFabricacao\":12-09-2023}";
        String idL2 = mercadoFacade.criaLote(jsonL1);
        ArrayList<Produto> targets = mercadoFacade.buscaProdutocomLote("leiTe inTegral");
        assertEquals(2, targets.size());
        targets = mercadoFacade.buscaProdutocomLote("leiTe desNatado");
        assertEquals(0, targets.size());
    }

}
