package usuario;

import controle.Carrinho;

public class ClientePremium extends Cliente {

	public ClientePremium(long id, String nome, String cpf, String endereco, String telefone) {
		super(id, nome, cpf, endereco, telefone);
        super.carrinho = new Carrinho(5, 3, 8);
	}
}