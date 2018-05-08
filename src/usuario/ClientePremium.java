package usuario;

import controle.Carrinho;

public class ClientePremium extends Cliente {
	
	// Cria um ClienteNormal com os mesmos atributos da classe mãe

	public ClientePremium(long id, String nome, String cpf, String endereco, String telefone) {
		
		super(id, nome, cpf, endereco, telefone);
		
		// Cria um carrinho com os respectivos valores de desconto de cada tipo de produto para o ClientePremium
		
        	super.carrinho = new Carrinho(5, 3, 8);
	}
}
