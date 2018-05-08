package controle;


import java.util.ArrayList;
import java.util.List;

import loja.Brinquedo;
import loja.Livro;
import loja.Presente;
import loja.Produto;

public class Carrinho {

    private double valorTotal; //armazena valor liquido da compra
    private double descontoLivro; //recebe o valor do desconto a ser aplicado no valor dos livros
    private double descontoPresente; //recebe o valor do desconto a ser aplicado no valor dos presentes
    private double descontoBrinquedo; //recebe o valor do desconto a ser aplicado no valor dos brinquedos

    protected List<Produto> produtos; //recebe todos os produtos adicionados no carrinho

    private Carrinho() {
        this.produtos = new ArrayList<>();
    }

    public Carrinho(double descontoLivro, double descontoPresente, double descontoBrinquedo) {
        this();
        this.descontoLivro = descontoLivro;
        this.descontoPresente = descontoPresente;
        this.descontoBrinquedo = descontoBrinquedo;
    }

    // Esta funcao adiciona um produto no carrinho dependendo do tipo dele, aplicando os descontos e acrescimos devidos  
    
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

    // Esta é o oposto da funcao de adiconarProduto  
    
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
