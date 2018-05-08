package visao;

import loja.Produto;
import usuario.Cliente;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public abstract class AbstractVisao {
    protected Nivel nivel;
    protected AbstractVisao proximo;

    public void setProximo(AbstractVisao proximo) {
        this.proximo = proximo;
    }

    public void executarAcao(Nivel nivel, List<Produto> produtos, List<Cliente> clientes) {
        if (this.nivel.ordinal() == nivel.ordinal()) {
            acao(produtos, clientes);
        }
        if (proximo != null) {
            proximo.executarAcao(nivel, produtos, clientes);
        }
    }

    protected abstract void acao(List<Produto> produtos, List<Cliente> clientes);

    /**
     * Formata decimais para o formato apresentado nos .csv
     *
     * Permite a conversao para formato com virgulas.
     *
     * @param str string
     * @return Number
     */
    protected static Number formataDecimais(String str) {
        DecimalFormat df = new DecimalFormat("#,00", DecimalFormatSymbols.getInstance(Locale.US));
        Number num = null;
        try {
            num = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return num;
    }

    protected static Double formataDouble(String str) {
        return formataDecimais(str).doubleValue();
    }
}

enum Nivel {
    BRINQUEDO,
    LIVRO,
    PRESENTE,
    CLIENTE,
    COMPRA,
    RELATORIO
}