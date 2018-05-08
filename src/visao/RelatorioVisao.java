package visao;

import controle.Carrinho;
import loja.Produto;
import usuario.Cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioVisao extends AbstractVisao {

    public RelatorioVisao(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    protected void acao(List<Produto> produtos, List<Cliente> clientes) {
        /**
         * resultado.csv
         * <id_cliente;valor_total_compra>
         * < id_cliente;id_item;tipo;quantidade;custo;valor_com_desconto>
         */
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("resultado.csv"));
            out.write(RelatorioVisao.gerarRelatorioCompraCSV(clientes));
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String gerarRelatorioCompra(final String separador, List<Cliente> clientes) {
        String relatorio = "";
        for (Cliente cliente : clientes) {
            Carrinho carrinho = cliente.getCarrinho();
            relatorio += String.format("%2$d" + "%1$s" + "%3$.2f\n",
                    separador, cliente.getId(), carrinho.getValorTotal());
            for (Produto produto : carrinho.getProdutos()) {
                double valorComDesconto = carrinho.getValorComDesconto();
                long idItem = produto.getId();
                relatorio += String.format("%2$d" + "%1$s" + "%3$d" + "%1$s" + "%4$d" +
                                "%1$s" + "%5$d" + "%1$s" + "%6$.2f" + "%1$s" + "%7$.2f\n",
                        separador, cliente.getId(), idItem,
                        produto.getTipo(), produto.getQuantidade(), produto.getCusto(), valorComDesconto);
            }
        }
        return relatorio;
    }

    private static String gerarRelatorioCompraCSV(List<Cliente> clientes) {
        return gerarRelatorioCompra(PONTO_VIRGULA, clientes);
    }
}