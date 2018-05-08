package controle;


import loja.Brinquedo;
import loja.Livro;
import loja.Presente;
import loja.Produto;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private double valorTotal;
    private double valorComDesconto;
    private double descontoLivro;
    private double descontoPresente;
    private double descontoBrinquedo;

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
            this.valorTotal += (p.getQuantidade() * p.getCusto()) * (1.15 - (this.descontoPresente / 100));
            this.valorComDesconto = (p.getQuantidade() * p.getCusto()) * (1.15 - (this.descontoPresente / 100));
        } else if (p instanceof Brinquedo) {
            this.valorTotal += (p.getQuantidade() * p.getCusto()) * (1 - (this.descontoBrinquedo / 100));
            this.valorComDesconto = (p.getQuantidade() * p.getCusto()) * (1 - (this.descontoBrinquedo / 100));
        } else if (p instanceof Livro) {
            this.valorTotal += (p.getQuantidade() * p.getCusto()) * (1.05 - (this.descontoLivro / 100));
            this.valorComDesconto = (p.getQuantidade() * p.getCusto()) * (1.05 - (this.descontoLivro / 100));
        }
        this.produtos.add(p);
    }

    public void removerProduto(Produto p) {
        if (p instanceof Presente) {
            this.valorTotal -= (p.getQuantidade() * p.getCusto()) * (1.15 - (this.descontoPresente / 100));
            this.valorComDesconto = (p.getQuantidade() * p.getCusto()) * (1.15 - (this.descontoPresente / 100));
        } else if (p instanceof Brinquedo) {
            this.valorTotal -= (p.getQuantidade() * p.getCusto()) * (1 - (this.descontoBrinquedo / 100));
            this.valorComDesconto = (p.getQuantidade() * p.getCusto()) * (1 - (this.descontoBrinquedo / 100));
        } else if (p instanceof Livro) {
            this.valorTotal -= (p.getQuantidade() * p.getCusto()) * (1.05 - (this.descontoLivro / 100));
            this.valorComDesconto = (p.getQuantidade() * p.getCusto()) * (1.05 - (this.descontoLivro / 100));
        }
        this.produtos.remove(p);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorComDesconto() {
        return valorComDesconto;
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