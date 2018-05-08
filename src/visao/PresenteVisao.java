package visao;

import loja.Presente;
import loja.Produto;
import usuario.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PresenteVisao extends AbstractVisao {

    public PresenteVisao(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    protected void acao(List<Produto> produtos, List<Cliente> clientes) {
        String nomeArqIn;
        try {
            BufferedReader br;
            int n;

            /**
             * presentes.csv
             * n
             * <id;nome;custo;fabricante>
             */
            nomeArqIn = "presentes.csv";
            br = new BufferedReader(new FileReader(nomeArqIn));
            n = Integer.valueOf(br.readLine());
            for (int i=0; i<n; i++) {
                String str[] = br.readLine().split(PONTO_VIRGULA);
                int index = 0;
                Presente presente = new Presente();
                presente.setId(Long.valueOf(str[index++]));
                presente.setNome(str[index++]);
                presente.setCusto(AbstractVisao.formataDouble(str[index++]));
                presente.setFabricante(str[index++]);
                produtos.add(presente);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}