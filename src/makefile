CC = javac
SRC = controle/Carrinho.java loja/Brinquedo.java loja/Livro.java loja/Presente.java loja/Produto.java usuario/Cliente.java usuario/ClienteNormal.java usuario/ClientePremium.java visao/Main.java visao/Visao.java
MAIN = visao/Main

all:
	$(CC) $(SRC)
clean:
	rm -rf *.class
	rm -rf */*.class
run: all
	java $(MAIN)
