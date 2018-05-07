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


public class Main {

    private static final String PONTO_VIRGULA = ";";

    private static Number formataDecimais(String str) {
        DecimalFormat df = new DecimalFormat("#,00", DecimalFormatSymbols.getInstance(Locale.US));
        Number num = null;
        try {
            num = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return num;
    }

    private static Double formataDouble(String str) {
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
    private static Produto removerProdutoPorId(long idProdutoHash, List<? extends Produto> produtos) {
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

    private static Cliente removerClientePorId(Long id, List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (id.equals(cliente.getId())) {
                clientes.remove(cliente);
                return cliente;
            }
        }
        return null;
    }

    private static String geraRelatorioCompra(final String separador, List<Cliente> clientes) {
//        <id_cliente;valor_total_compra>
//        < id_cliente;id_item;tipo;quantidade;custo;valor_com_desconto>
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

    private static String geraRelatorioCompraCSV(List<Cliente> clientes) {
        return geraRelatorioCompra(PONTO_VIRGULA, clientes);
    }

    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));

        List<Produto> brinquedos = new ArrayList<>();
        List<Produto> livros = new ArrayList<>();
        List<Produto> presentes = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        //TODO aplicar padrao Chain of Responsability na leitura dos arquivos
        //TODO aplicar Singleton na Main

        //brinquedos.csv, livros.csv, presentes.csv, clientes.csv e
        //compras.csv

        String nomeArqIn;
        try {
            BufferedReader br;
            int n;

            nomeArqIn = "brinquedos.csv";
            //<id;nome;custo;fabricante;idade_minima>
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

            nomeArqIn = "livros.csv";
            //<id;nome;custo;editora;ISBN>
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

            nomeArqIn = "presentes.csv";
//            <id;nome;custo;fabricante>
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

            nomeArqIn = "clientes.csv";
//            <id;TIPO;nome;cpf;endereço;telefone>
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

            nomeArqIn = "compras.csv";
//            <id_cliente;id_produto;TIPO;quantidade>
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
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("resultado.csv"));
            out.write(geraRelatorioCompraCSV(clientes));
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}