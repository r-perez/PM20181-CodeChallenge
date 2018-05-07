package loja;

public class Brinquedo extends Produto {
    private String fabricante;
    private int idadeMinima;

    public static final int TIPO = 1;

    @Override
    public int getTipo() {
        return Brinquedo.TIPO;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getIdadeMinima() {
        return idadeMinima;
    }

    public void setIdadeMinima(int idadeMinima) {
        this.idadeMinima = idadeMinima;
    }

}
