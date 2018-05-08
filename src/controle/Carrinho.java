package controle;


import java.util.ArrayList;
import java.util.List;

import loja.Brinquedo;
import loja.Livro;
import loja.Presente;
import loja.Produto;

public class Carrinho {

    private double valorTotal; //armazena valor bruto das compras 
    private double descontoLivro; //recebe valor do descontro a ser aplicado no valor do do livro
    private double descontoPresente; //recebe valor do descontro a ser aplicado no valor dopresente
    private double descontoBrinquedo; //recebe valor do descontro a ser aplicado no valor do brinquedo

    protected List<Produto> produtos;

    private Carrinho() {
        this.produtos = new ArrayList<>();
    }

    public Carrinho(double descontoLivro, double descontoPresente, double descontoBrinquedo) {
        this();
        this.descontoLivro = descontoLivro;
        this.descontoPresente = descontoPresente;
        this.descontoBrinquedo = descontoBrinquedo;
    }

    public void adicionarProduto(Produto p) {
        if (p instanceof Presente) {
            valorTotal += (p.getQuantidade() * p.getCusto()) * (1.15 - (descontoPresente / 100));
        } else if (p instanceof Brinquedo) {
            valorTotal += (p.getQuantidade() * p.getCusto()) * (1 - (descontoBrinquedo / 100));
        } else if (p instanceof Livro) {
            valorTotal += (p.getQuantidade() * p.getCusto()) * (1.05 - (descontoLivro / 100));
        }
        produtos.add(p);
    }

    public void removerProduto(Produto p) {
        if (p instanceof Presente) {
            valorTotal -= (p.getQuantidade() * p.getCusto()) * (1.15 - (descontoPresente / 100));
        } else if (p instanceof Brinquedo) {
            valorTotal -= (p.getQuantidade() * p.getCusto()) * (1 - (descontoBrinquedo / 100));
        } else if (p instanceof Livro) {
            valorTotal -= (p.getQuantidade() * p.getCusto()) * (1.05 - (descontoLivro / 100));
        }
        produtos.remove(p);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDescontoLivro() {
        return descontoLivro;
    }

    public double getDescontoPresente() {
        return descontoPresente;
    }

    public double getDescontoBrinquedo() {
        return descontoBrinquedo;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
