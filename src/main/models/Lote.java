package main.models;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Lote {

    private String id;

    private Produto produto;

    private int quantidade;

    private Date dataFabricacao;

    private String stringDataFabricacao;

    public Lote(Produto produto, int quantidade, Date dataFabricacao) {
        this.id = UUID.randomUUID().toString();
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataFabricacao = dataFabricacao;
        this.stringDataFabricacao = defineDataFabricacaoString();
    }

    public String getId() {
        return this.id;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public Date getDataFabricacao() {
        return this.dataFabricacao;
    }

    public String getDataFabricacaoString(){return this.stringDataFabricacao;}

    public String toString() {
        return "Lote ID: " + getId() + " - Produto: " + getProduto().getNome()
                + " - " + getQuantidade() + " itens"
                + " - " + "Validade: " + this.stringDataFabricacao;
    }

    private String defineDataFabricacaoString() {
        Date data = getDataFabricacao();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lote lote = (Lote) o;
        return Objects.equals(id, lote.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
