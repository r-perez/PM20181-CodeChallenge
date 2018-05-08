package loja;

public abstract class Produto {

    protected long id; // armazena o id do produto
    protected String nome; // armazena o nome do produto
    protected double custo; // armazena o custo do produto
    protected int quantidade; // armazena a quantidade do produto

    /**
     * Retorna o tipo do Produto, atributo obrigatorio, variavel de instancia.
     * @return int
     */
    public abstract int getTipo();

    /**
     * Nao eh possivel alterar o tipo do produto pois eh uma variavel de instancia.
     */
    private void setTipo() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
