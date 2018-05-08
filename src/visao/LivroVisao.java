package visao;

import loja.Livro;
import loja.Produto;
import usuario.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LivroVisao extends AbstractVisao {

    public LivroVisao(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    protected void acao(List<Produto> produtos, List<Cliente> clientes) {
        String nomeArqIn;
        try {
            BufferedReader br;
            int n;

            /**
             * livros.csv
             * n
             * <id;nome;custo;editora;ISBN>
             */
            nomeArqIn = "livros.csv";
            br = new BufferedReader(new FileReader(nomeArqIn));
            n = Integer.valueOf(br.readLine());
            for (int i=0; i<n; i++) {
                String str[] = br.readLine().split(PONTO_VIRGULA);
                int index = 0;
                Livro livro = new Livro();
                livro.setId(Long.valueOf(str[index++]));
                livro.setNome(str[index++]);
                livro.setCusto(AbstractVisao.formataDouble(str[index++]));
                livro.setEditora(str[index++]);
                livro.setISBN(str[index++]);
                produtos.add(livro);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}