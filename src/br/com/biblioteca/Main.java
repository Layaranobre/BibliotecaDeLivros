// Unica classe que inicia com todas as opções marcadas no eclipse

package br.com.biblioteca;

public class Main {

	public Main() {
	}
	
	public static void main(String[] args) {
		//Criamos um objeto da classe Menu. "new Menu()" cria uma nova instância do Menu. | A variável "menu" passa a guardar esse objeto.
		Menu menu = new Menu();
		
		//Chamamos o metodo iniciar e a partir de agora o MENU começa a funcionar. 
		menu.iniciar();


	}

}
