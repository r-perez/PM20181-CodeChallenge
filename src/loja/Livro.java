package loja;

public class Livro extends Produto {
	private String editora; // recebe o nome da editora
	private String ISBN; // recebe o ISBN do livro
    public static final int TIPO = 2; // define um tipo especifico para a subclasse Livro

    @Override
    public int getTipo() {
        return Livro.TIPO;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
