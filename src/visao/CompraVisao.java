package visao;

import loja.Brinquedo;
import loja.Livro;
import loja.Presente;
import loja.Produto;
import usuario.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CompraVisao extends AbstractVisao {

    public CompraVisao(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    protected void acao(List<Produto> produtos, List<Cliente> clientes) {
        String nomeArqIn;
        try {
            BufferedReader br;
            int n;

            /**
             * compras.csv
             * n
             * <id_cliente;id_produto;tipo;quantidade>
             *
             * Tipo: 1) Brinquedo, 2) Livro, 3) Presente
             */
            nomeArqIn = "compras.csv";
            br = new BufferedReader(new FileReader(nomeArqIn));
            n = Integer.valueOf(br.readLine());
            for (int i=0; i<n; i++) {
                String str[] = br.readLine().split(";");
                int index = 0;

                long idCliente = Long.valueOf(str[index++]);
                long idProduto = Long.valueOf(str[index++]);
                int tipo = Integer.valueOf(str[index++]);
                int quantidade = Integer.valueOf(str[index++]);

                Cliente cliente = removerClientePorId(idCliente, clientes);
                Produto produto = removerProdutoPorId(idProduto, tipo, produtos);
                produto.setQuantidade(quantidade);
                cliente.getCarrinho().adicionarProduto(produto);

                clientes.add(cliente);
                produtos.add(produto);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * Encontra um produto dado idProdutoHash, o tipo e a lista de produtos.
     * Assume que o idProdutoHash eh de fato um hash, e dessa forma usa um mod com o numero de
     * tipos de produto para encontrar o id correspondente.
     *
     * @param idProdutoHash "hash" da id do produto
     * @param tipo tipo do produto
     * @param produtos lista de produtos
     * @return Produto
     */
    protected Produto removerProdutoPorId(long idProdutoHash, int tipo, List<? extends Produto> produtos) {
        Class[] classesProduto = new Class[]{Brinquedo.class, Livro.class, Presente.class};
        long novoId = (idProdutoHash % classesProduto.length) + 1;
        for (Produto produto : produtos) {
            if (produto.getId() == novoId && produto.getTipo() == tipo) {
                produtos.remove(produto);
                return produto;
            }
        }
        return null;
    }

    protected Cliente removerClientePorId(Long id, List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (id.equals(cliente.getId())) {
                clientes.remove(cliente);
                return cliente;
            }
        }
        return null;
    }
}