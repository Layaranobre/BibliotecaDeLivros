//Aqui vão ficar a parte do " OQUE O SISTEMA VAI FAZER" Remover, adicionar, alterar e outros 
// PRIVATE = só pode ser acessada diretamente dentro da própria classe Biblioteca.
// new = Cria um objeto
// new ArrayList<Livro>() = cria uma lista que poderá armazenar Livros.

package br.com.biblioteca;
import java.util.ArrayList;  // Apareceu depois que criei o private ArrayList<Livro> livros; (Obrigatório)
public class Biblioteca {
	
	private ArrayList<Livro> livros;  // Array list armazena varios tipos de objetos, nesse caso (uma lista que vai armazenar objetos da classe Livro)
		
    //Construtor, geralmente já é criado junto no eclipse se pedir.
	public Biblioteca() {
		livros = new ArrayList<Livro>();}
	
	// Cadastrar livro-------------------------------------
	//(Livro livro): Significa Livro = tipo do objeto e livro = nome da variável
	//Depois usamos add() para colocar esse livro dentro da lista.
	public void cadastrarLivro(Livro livro) {
        livros.add(livro);}
	
	//Listar Livros----------------------------------------
	//O (for) percorre cada elemento da lista.
	// "Livro livro" significa que, a cada repetição, a variável "livro" representa um dos livros que está dentro da lista.
	 public void listarLivros() {   // Sua função é percorrer todos os livros cadastrados e mostrar suas informações.
		 for (Livro livro : livros) {
			 System.out.println(livro);}}
		 
	// Buscar Livros ---------------------------------------
     public Livro buscarLivro(String titulo) { // recebe o título que queremos procurar, String titulo = texto que o usuário informou, por isso usamos public Livro e não public void. 
    	 for (Livro livro : livros) {  //Percorre todos os livros da biblioteca.
		    
    		 //getTitulo() permite pegar o título do livro, mesmo que o atributo "titulo" seja private.
    		 //equalsIgnoreCase() compara dois textos ignorando diferença entre letras maiúsculas e minúsculas. resulta em true
    		 if (livro.getTitulo().equalsIgnoreCase(titulo)) {
    			 return livro;}   // return devolve esse objeto Livro para quem chamou o método.
    		 }
    	         
    	 	 return null;  //null significa que não temos nenhum objeto Livro para retornar. Caso não ache o livro	 
     }
     
     //Remover Livro----------------------------------------
     //Primeiro vamos procurar o livro. Depois, se ele existir, vamos removê-lo da lista.
     public void removerLivro(String titulo) { //Esse método recebe o título do livro que queremos remover
    	 Livro livro = buscarLivro(titulo); //Chamamos o metodo buscar livro() que criamos acima (ou seja podemos reutilizar o metodo ao inves que respetir todo o codigo)~
    	 
    	 
    	 if(livro != null) {  //Remove objeto da lista.
    		 livros.remove(livro);  //remove() remove o objeto da lista.
    		 System.out.println("Livro removido com sucesso!");
    	 } else { System.out.println("Livro não encontrado.");}
     }
   	                                  
}


