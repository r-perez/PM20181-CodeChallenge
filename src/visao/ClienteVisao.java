package visao;

import loja.Produto;
import usuario.Cliente;
import usuario.ClienteNormal;
import usuario.ClientePremium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ClienteVisao extends AbstractVisao {

    public ClienteVisao(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    protected void acao(List<Produto> produtos, List<Cliente> clientes) {
        String nomeArqIn;
        try {
            BufferedReader br;
            int n;

            /**
             * clientes.csv
             * n
             * <id;tipo;nome;cpf;endereço;telefone>
             */
            nomeArqIn = "clientes.csv";
            br = new BufferedReader(new FileReader(nomeArqIn));
            n = Integer.valueOf(br.readLine());
            for (int i=0; i<n; i++) {
                String str[] = br.readLine().split(PONTO_VIRGULA);
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
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}