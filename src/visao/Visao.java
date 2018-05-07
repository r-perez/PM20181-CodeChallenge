package visao;

import controle.Carrinho;
import loja.Brinquedo;
import loja.Livro;
import loja.Presente;
import loja.Produto;
import usuario.Cliente;
import usuario.ClienteNormal;
import usuario.ClientePremium;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Uma lazy-loader singleton para a visao.
 * <br/>
 * <a href=https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom>Initialization-on-demand holder idiom - Wikipedia</a>
 * <br/>
 * <a href=https://en.wikipedia.org/wiki/Lazy_initialization>Lazy initialization - Wikipedia</a>
 * <br/>
 * <a href=https://en.wikipedia.org/wiki/Singleton_pattern>Singleton pattern - Wikipedia</a>
 * <br/>
 */
public class Visao {

    private static final String PONTO_VIRGULA = ";";

    private Visao() {}

    private static class LazyHolder {
        static final Visao INSTANCE = new Visao();
    }

    public static Visao getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Formata decimais para o formato apresentado nos .csv
     *
     * Permite a conversao para formato com virgulas.
     *
     * @param str
     * @return
     */
    private Number formataDecimais(String str) {
        DecimalFormat df = new DecimalFormat("#,00", DecimalFormatSymbols.getInstance(Locale.US));
        Number num = null;
        try {
            num = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return num;
    }

    private Double formataDouble(String str) {
        return formataDecimais(str).doubleValue();
    }

    /**
     *
     * Encontra um produto dado idProdutoHash a a lista de produtos.
     * Assume que o idProdutoHash eh de fato um hash, e dessa forma usa um mod com o numero de
     * tipos de produto para encontrar o id correspondente.
     *
     * @param idProdutoHash "hash" da id do produto
     * @param produtos lista de produtos
     * @return Produto
     */
    private Produto removerProdutoPorId(long idProdutoHash, List<? extends Produto> produtos) {
        Class[] classesProduto = new Class[]{Brinquedo.class, Livro.class, Presente.class};
        long novoId = (idProdutoHash % classesProduto.length) + 1;
        for (Produto produto : produtos) {
            if (produto.getId() == novoId) {
                produtos.remove(produto);
                return produto;
            }
        }
        return null;
    }

    private Cliente removerClientePorId(Long id, List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (id.equals(cliente.getId())) {
                clientes.remove(cliente);
                return cliente;
            }
        }
        return null;
    }

    private String geraRelatorioCompra(final String separador, List<Cliente> clientes) {
        String relatorio = "";
        for (Cliente cliente : clientes) {
            Carrinho carrinho = cliente.getCarrinho();
            relatorio += String.format("%2$d" + "%1$s" + "%3$.2f\n",
                    separador, cliente.getId(), carrinho.getValorTotal());
            for (Produto produto : carrinho.getProdutos()) {
                relatorio += String.format("%2$d" + "%1$s" + "%3$d" + "%1$s" + "%4$d" +
                                "%1$s" + "%5$d" + "%1$s" + "%6$.2f" + "%1$s" + "%7$.2f\n",
                        separador, cliente.getId(), produto.getId(),
                        produto.getTipo(), produto.getQuantidade(), produto.getCusto(), 0.0d);
            }
        }
        return relatorio;
    }

    private String geraRelatorioCompraCSV(List<Cliente> clientes) {
        return geraRelatorioCompra(PONTO_VIRGULA, clientes);
    }

    public void main() {
        List<Produto> brinquedos = new ArrayList<>();
        List<Produto> livros = new ArrayList<>();
        List<Produto> presentes = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        //TODO aplicar padrao Chain of Responsability na leitura dos arquivos
        //TODO aplicar Singleton na Main

        /**
         * 4.
         * Ao iniciar o programa, deve ser efetuada a leitura dos arquivos de entrada e o cálculo do valor
         * total a ser pago por cada cliente. Os arquivos de entrada estão no formato CSV, separados por ponto e
         * vírgula. Devem ser lidos os arquivos: brinquedos.csv, livros.csv, presentes.csv, clientes.csv e
         * compras.csv.
         */
        String nomeArqIn;
        try {
            BufferedReader br;
            int n;

            /**
             * brinquedos.csv
             * n
             * <id;nome;custo;fabricante;idade_minima>
             */
            nomeArqIn = "brinquedos.csv";
            br = new BufferedReader(new FileReader(nomeArqIn));
            n = Integer.valueOf(br.readLine());
            for (int i=0; i<n; i++) {
                String str[] = br.readLine().split(";");
                int index = 0;
                Brinquedo brinquedo = new Brinquedo();
                brinquedo.setId(Long.valueOf(str[index++]));
                brinquedo.setNome(str[index++]);
                brinquedo.setCusto(formataDouble(str[index++]));
                brinquedo.setFabricante(str[index++]);
                brinquedo.setIdadeMinima(Integer.valueOf(str[index++]));
                brinquedos.add(brinquedo);
            }

            /**
             * livros.csv
             * n
             * <id;nome;custo;editora;ISBN>
             */
            nomeArqIn = "livros.csv";
            br = new BufferedReader(new FileReader(nomeArqIn));
            n = Integer.valueOf(br.readLine());
            for (int i=0; i<n; i++) {
                String str[] = br.readLine().split(";");
                int index = 0;
                Livro livro = new Livro();
                livro.setId(Long.valueOf(str[index++]));
                livro.setNome(str[index++]);
                livro.setCusto(formataDouble(str[index++]));
                livro.setEditora(str[index++]);
                livro.setISBN(str[index++]);
                livros.add(livro);
            }

            /**
             * presentes.csv
             * n
             * <id;nome;custo;fabricante>
             */
            nomeArqIn = "presentes.csv";
            br = new BufferedReader(new FileReader(nomeArqIn));
            n = Integer.valueOf(br.readLine());
            for (int i=0; i<n; i++) {
                String str[] = br.readLine().split(";");
                int index = 0;
                Presente presente = new Presente();
                presente.setId(Long.valueOf(str[index++]));
                presente.setNome(str[index++]);
                presente.setCusto(formataDouble(str[index++]));
                presente.setFabricante(str[index++]);
                presentes.add(presente);
            }

            /**
             * clientes.csv
             * n
             * <id;tipo;nome;cpf;endereço;telefone>
             */
            nomeArqIn = "clientes.csv";
            br = new BufferedReader(new FileReader(nomeArqIn));
            n = Integer.valueOf(br.readLine());
            for (int i=0; i<n; i++) {
                String str[] = br.readLine().split(";");
                int index = 0;
                Cliente cliente;
                switch (Integer.valueOf(str[0])) {
                    case 1:
                        cliente = new ClienteNormal(Long.valueOf(str[index++]), str[index++], str[index++], str[index++], str[index++]);
                        break;
                    case 2:
                        cliente = new ClientePremium(Long.valueOf(str[index++]), str[index++], str[index++], str[index++], str[index++]);
                        break;
                    default:
                        cliente = null;
                        break;
                }
                clientes.add(cliente);
            }

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
                Produto produto;
                switch (tipo) {
                    case Brinquedo.TIPO:
                        produto = removerProdutoPorId(idProduto, brinquedos);
                        break;
                    case Livro.TIPO:
                        produto = removerProdutoPorId(idProduto, livros);
                        break;
                    case Presente.TIPO:
                        produto = removerProdutoPorId(idProduto, presentes);
                        break;
                    default:
                        produto = null;
                        break;
                }
                produto.setQuantidade(quantidade);
                cliente.getCarrinho().adicionarProduto(produto);

                clientes.add(cliente);
                switch (tipo) {
                    case Brinquedo.TIPO:
                        brinquedos.add(produto);
                        break;
                    case Livro.TIPO:
                        livros.add(produto);
                        break;
                    case Presente.TIPO:
                        presentes.add(produto);
                        break;
                    default:
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        /**
         * 5.
         * O sistema deve imprimir o relatório de compra em um arquivo chamado resultado.csv. Para o
         * relatório de compra, o sistema deve apresentar a listagem com os quantitativos, produtos, valor bruto
         * e valor com desconto para os itens de uma compra (de um cliente). Ao final da compra de cada cliente,
         * devem sair na listagem o código do cliente e o valor final a ser pago.
         *
         * resultado.csv
         * <id_cliente;valor_total_compra>
         * < id_cliente;id_item;tipo;quantidade;custo;valor_com_desconto>
         */
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("resultado.csv"));
            out.write(geraRelatorioCompraCSV(clientes));
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}