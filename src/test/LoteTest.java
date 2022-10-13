package test;

import static org.junit.jupiter.api.Assertions.*;

import main.models.Lote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.facades.Facade;

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
    public void verifyLoteDataFabricacao() {
        assertEquals(1, mercadoFacade.listaLotes().size());
        Lote lotezinho = mercadoFacade.listaLotes().toArray(new Lote[0])[0];
        assertEquals(lotezinho.getDataFabricacaoString(),  "12/09/2023");
    }

    @Test
    public void verifyLoteAdicionado() {
        assertEquals(1, mercadoFacade.listaLotes().size());
    }

    @Test
    public void verifyProdutoDeletado() {
        assertEquals(1, mercadoFacade.listaLotes().size());
        mercadoFacade.removeProduto(idP1);
        assertEquals(0, mercadoFacade.listaLotes().size());
    }

}
