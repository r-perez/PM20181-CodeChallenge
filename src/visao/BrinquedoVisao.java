package visao;

import loja.Brinquedo;
import loja.Produto;
import usuario.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public final class BrinquedoVisao extends AbstractVisao {

    public BrinquedoVisao(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    protected void acao(List<Produto> produtos, List<Cliente> clientes) {
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
                brinquedo.setCusto(AbstractVisao.formataDouble(str[index++]));
                brinquedo.setFabricante(str[index++]);
                brinquedo.setIdadeMinima(Integer.valueOf(str[index++]));
                produtos.add(brinquedo);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}