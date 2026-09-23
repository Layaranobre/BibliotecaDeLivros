//Aqui você vai somente declarar as coisas sem precisar colocar as informações do livro (COMO VAI APARECER E DECLARAR AS COISAS QUE VAI USAR)

package br.com.biblioteca;

public class Livro {
	
	//Dados do Livro
	public String titulo;
	public String autor;
	public String editora;
	public String isbn; //Pode ser guardado como String ou como long ex: 5661611616L
	public int anoPublicacao;
	public boolean disponibilidade;
	
	//Construtor (Para criar o livro) precisa declarar todos que estão acima.
	public Livro(String titulo, String autor, String editora, String isbn,
				int anoPublicacao, boolean disponibilidade) {
	this.titulo = titulo;
	this.autor = autor;
	this.editora = editora;
	this.isbn = isbn;
	this.anoPublicacao = anoPublicacao;
	this.disponibilidade = disponibilidade;}
	
	
	
	//GETTER (Permite ler) e SETTER (Permite alterar ) Quando usamos PRIVATE não precisa do set e get
	public String getTitulo() {    //aqui você só vai criar o Get, porque quando for chamar será em outra class
		return titulo;             //Estou criando um método chamado getTitulo que vai devolver o título deste livro. O RETURN, permite retornar
	}

	
	public void setTitulo(String titulo) {    //aqui você só vai criar o Set, porque quando for chamar será em outra class
	    this.titulo = titulo;                 // Void quer dizer que não precisa de Return 
	}                                         //O this significa "Coloque o valor recebido no atributo titulo deste objeto."

	
	 // TOSTRING (Formata a exibição do objeto) (Coloquei um embaixo do outro para entender melhor)
	@Override
    public String toString() {
        return "Livro: " + titulo + 
        	   " Autor: " + autor + 
        	   " Editora: " + editora +
        	   " ISBN: " + isbn +
        	   " Ano: "  + anoPublicacao +
        	   " Disponivel: " + disponibilidade;}
 }
