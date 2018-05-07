package loja;

public class Presente extends Produto {
    private String fabricante;
    public static final int TIPO = 3;

    @Override
    public int getTipo() {
        return Presente.TIPO;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
