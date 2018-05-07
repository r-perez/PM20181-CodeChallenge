package loja;

public class Livro extends Produto {
	private String editora;
	private String ISBN;
    public static final int TIPO = 2;

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
