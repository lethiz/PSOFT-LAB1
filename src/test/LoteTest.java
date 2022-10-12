package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.facades.Facade;

class LoteTest {

    private Facade mercadoFacade = new Facade();

    private String jsonP1 = "{\"nome\":\"Leite integral\", \"fabricante\":\"Parmalat\", \"preco\":10.5}";

    private String idP1;

    String jsonL1 = "{\"idProduto\":\"" + idP1 + "\", \"quantidade\":10}";
    private String idL1;

    @BeforeEach
    public void createLote() {
        this.idP1 = mercadoFacade.criaProduto(jsonP1);
        this.idL1 = mercadoFacade.criaLote(jsonL1);
    }

    @Test
    public void verifyLoteAdicionado() {
        assertEquals(1, mercadoFacade.listaLotes().size());
    }
}
