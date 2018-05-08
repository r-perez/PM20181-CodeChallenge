package loja;

public class Brinquedo extends Produto {
    private String fabricante; //recebe o nome do fabricante ou marca
    private int idadeMinima; //recebe a idade minima para jogar

    public static final int TIPO = 1; // define um tipo especifico para a subclasse Brinquedo

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
