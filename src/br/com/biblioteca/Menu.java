// Essa classe serve para (Conversar com o usuário), receber o que ele digitar e chamar os métodos da Biblioteca.

package br.com.biblioteca;

import java.util.List;
import java.util.Scanner; //Apareceu depois que criei o private Scanner scanner; (Obrigatório)

public class Menu {
	 private Scanner scanner;   	// Scanner será usado para ler o que o usuário digitar no teclado.
	 private Biblioteca biblioteca;// Criamos um objeto da classe Biblioteca.  É através dele que vamos cadastrar, listar, buscar e remover livros.
	 private LivroDAO dao;        //Se comunica com o LivroDAO

	 
	 // =========================================================
	 // CONSTRUTOR
	// =========================================================	 
	public Menu() {
		scanner = new Scanner(System.in); // Cria o Scanner para receber informações do usuário.
		biblioteca = new Biblioteca();  // Cria uma nova Biblioteca. Dentro dela, o ArrayList de livros também será criado.
		dao = new LivroDAO();
	}
	
	public void iniciar() {
	    /* O while mantém o menu funcionando. Enquanto a condição for verdadeira, o menu continuará aparecendo.
         * Quando o usuário escolher a opção 0, vamos mudar a variável para false e o programa será encerrado. */
		boolean executando = true;
		while (executando) {
			 System.out.println("\n===== BIBLIOTECA =====");
	         System.out.println("1 - Cadastrar livro");
	         System.out.println("2 - Listar livros");
	         System.out.println("3 - Buscar livro");
	         System.out.println("4 - Remover livro");
	         System.out.println("0 - Sair");
	         System.out.print("Escolha uma opção: ");
	      
	         
	         int opcao = scanner.nextInt();  //nextInt() lê um número inteiro digitado pelo usuário.
	         scanner.nextLine(); //Depois de usar nextInt(), usamos nextLine() para consumir a quebra de linha que ficou no Scanner. Isso evita problemas quando formos ler um texto Logo depois

	         // SWICH verifica qual opção o usuario escolheu. 
	         
	         switch (opcao) {

             case 1:

                 cadastrarLivro();

                 break;


             case 2:

                 listarLivros();

                 break;


             case 3:

                 buscarLivro();

                 break;


             case 4:

                 removerLivro();

                 break;


             case 0:
            	 
            	 executando = false;
            	 System.out.println("Programa encerrado.");
            	 
            	 break;


             default: // caso o usuario digite algo que não existe.
            	 
                 System.out.println("Opção inválida.");
                 }
	         }
        scanner.close();} //Fecha o scanner quando o programa termina. 
	
    //=========================================================
	//CADASTRAR LIVRO
    //=========================================================
	
	//Aqui começamos a pedir as informações do livro para o usuário. 
	private void cadastrarLivro() { 
		 System.out.print("Digite o título: ");
	        String titulo = scanner.nextLine();

	        System.out.print("Digite o autor: ");
	        String autor = scanner.nextLine();

	        System.out.print("Digite a editora: ");
	        String editora = scanner.nextLine();

	        System.out.print("Digite o ISBN: ");
	        String isbn = scanner.nextLine();

	        System.out.print("Digite o ano de publicação: ");
	        String anoPublicacao = scanner.nextLine();
	      
	        
	        
	        scanner.nextLine(); //Consumimos a quebra de linha que ficou no Scanner.
	        
	        // Criamos um novo objeto Livro utilizando o construtor que você criou na primeira classe.
	        Livro livro = new Livro(
	                titulo,
	                autor,
	                editora,
	                isbn,
	                anoPublicacao,
	                true);
	        
	        // Salva o livro no MySQL
	        dao.cadastrar(livro);
	        
			 //Enviamos o objeto Livro para a Biblioteca. A Biblioteca será reponsavel por coloca-lo dentro do Array List.
			        biblioteca.cadastrarLivro(livro);
			}
	
			 // =========================================================
		    // LISTAR LIVROS DO MYSQL
		    // =========================================================
		
		    private void listarLivros() {
		
		        List<Livro> livros = dao.listar();
		
		
		        if (livros.isEmpty()) {
		
		            System.out.println("Nenhum livro cadastrado no banco.");
		
		            return;
		        }
		
		
		        System.out.println("\n===== LIVROS CADASTRADOS =====");
		
		
		        for (Livro livro : livros) {
		
		            System.out.println(livro);
		        }
		    }
			
	
	
		    //============================================================
			//BUSCAR LIVRO
		    //============================================================
		    
			//Chamamos buscarLivro() da Biblioteca. O metodo vai procurar o livro pelo titulo. se encontrar retorna o livro, se não retorna null. 
			 private void buscarLivro() {
			        System.out.print("Digite o título do livro: ");
			        String titulo = scanner.nextLine();
			        
			        Livro livro = dao.buscarPorTitulo(titulo);
	        
		    //Verificamos se encontramos um livro.
	        
	        if (livro != null) {
	        	System.out.println("\n===== LIVRO ENCONTRADO =====");
	            System.out.println(livro); //Como você criou o toString() na classe Livro, podemos simplesmente imprimir o objeto.
	        } else {
	        	System.out.println("Livro não encontrado.");
	        	}
	       }
			 
			 
			 
			 

		    // =========================================================
		    // REMOVER LIVRO
		    // =========================================================
			 private void removerLivro() {
	
		        System.out.print("Digite o título do livro que deseja remover: ");
	
		        String titulo = scanner.nextLine();
	
		         //Pedimos para a Biblioteca remover o livro. A Biblioteca será responsável por procurar o livo. e utilizar o remove().
		        dao.removerPorTitulo(titulo);
		        
	    }

}
