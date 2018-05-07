package usuario;

import controle.Carrinho;

public class ClienteNormal extends Cliente {

	public ClienteNormal(long id, String nome, String cpf, String endereco, String telefone) {
		super(id, nome, cpf, endereco, telefone);
		super.carrinho = new Carrinho(3, 2, 5);
	}
}
