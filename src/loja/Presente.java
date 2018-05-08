package loja;

public class Presente extends Produto {
    private String fabricante; // recebe o nome do fabricante
    public static final int TIPO = 3; // define um tipo especifico para a subclasse Produto

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
