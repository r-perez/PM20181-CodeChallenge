package usuario;

import controle.Carrinho;

public class ClienteNormal extends Cliente {

	// Cria um ClienteNormal com os mesmos atributos da classe mãe
	
	public ClienteNormal(long id, String nome, String cpf, String endereco, String telefone) {
		
		super(id, nome, cpf, endereco, telefone);
		
		// Cria um carrinho com os respectivos valores de desconto de cada tipo de produto para o Cliente Normal		
		super.carrinho = new Carrinho(3, 2, 5);
	}
}
