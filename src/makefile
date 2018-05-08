CC = javac
SRC = controle/Carrinho.java loja/Brinquedo.java loja/Livro.java loja/Presente.java loja/Produto.java usuario/Cliente.java usuario/ClienteNormal.java usuario/ClientePremium.java visao/AbstractVisao.java visao/BrinquedoVisao.java visao/ClienteVisao.java visao/CompraVisao.java visao/LivroVisao.java visao/Main.java visao/PresenteVisao.java visao/RelatorioVisao.java
MAIN = visao/Main

all:
	$(CC) $(SRC)
clean:
	rm -rf *.class
	rm -rf */*.class
run: all
	java $(MAIN)
