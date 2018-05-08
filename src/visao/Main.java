package visao;

import loja.Produto;
import usuario.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Main usando o padrao de projeto de Cadeia de responsabilidades.
 *
 * https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern
 */
public final class Main {

    private static AbstractVisao getCadeiaDeVisoes() {
        AbstractVisao brinquedoVisao = new BrinquedoVisao(Nivel.BRINQUEDO);
        AbstractVisao livroVisao = new LivroVisao(Nivel.LIVRO);
        AbstractVisao presenteVisao = new PresenteVisao(Nivel.PRESENTE);
        AbstractVisao clienteVisao = new ClienteVisao(Nivel.CLIENTE);
        AbstractVisao compraVisao = new CompraVisao(Nivel.COMPRA);
        AbstractVisao relatorioVisao = new RelatorioVisao(Nivel.RELATORIO);

        brinquedoVisao.setProximo(livroVisao);
        livroVisao.setProximo(presenteVisao);
        presenteVisao.setProximo(clienteVisao);
        clienteVisao.setProximo(compraVisao);
        compraVisao.setProximo(relatorioVisao);
        return brinquedoVisao;
    }

    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        AbstractVisao cadeiaDeVisoes = getCadeiaDeVisoes();

        /**
         * 4.
         * Ao iniciar o programa, deve ser efetuada a leitura dos arquivos de entrada e o cálculo do valor
         * total a ser pago por cada cliente. Os arquivos de entrada estão no formato CSV, separados por ponto e
         * vírgula. Devem ser lidos os arquivos: brinquedos.csv, livros.csv, presentes.csv, clientes.csv e
         * compras.csv.
         */
        cadeiaDeVisoes.executarAcao(Nivel.BRINQUEDO, produtos, clientes);
        cadeiaDeVisoes.executarAcao(Nivel.LIVRO, produtos, clientes);
        cadeiaDeVisoes.executarAcao(Nivel.PRESENTE, produtos, clientes);
        cadeiaDeVisoes.executarAcao(Nivel.CLIENTE, produtos, clientes);
        cadeiaDeVisoes.executarAcao(Nivel.COMPRA, produtos, clientes);

        /**
         * 5.
         * O sistema deve imprimir o relatório de compra em um arquivo chamado resultado.csv. Para o
         * relatório de compra, o sistema deve apresentar a listagem com os quantitativos, produtos, valor bruto
         * e valor com desconto para os itens de uma compra (de um cliente). Ao final da compra de cada cliente,
         * devem sair na listagem o código do cliente e o valor final a ser pago.
         */
        cadeiaDeVisoes.executarAcao(Nivel.RELATORIO, produtos, clientes);
    }
}