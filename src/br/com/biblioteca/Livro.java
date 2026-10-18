//Aqui você vai somente declarar as coisas sem precisar colocar as informações do livro (COMO VAI APARECER E DECLARAR AS COISAS QUE VAI USAR)

package br.com.biblioteca;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livro {

	// ID do livro no banco de dados ou seja cria um ID unico para o livro.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//Dados do Livro
	public String titulo;
	public String autor;
	public String editora;
	public String isbn; //Pode ser guardado como String ou como long ex: 5661611616L
	public String anoPublicacao;
	public boolean disponibilidade;

	// Construtor vazio necessário para o JPA
	public Livro() {
	}

	//Construtor (Para criar o livro) precisa declarar todos que estão acima.
	public Livro(String titulo, String autor, String editora, String isbn,
				String anoPublicacao, boolean disponibilidade) {
	this.titulo = titulo;
	this.autor = autor;
	this.editora = editora;
	this.isbn = isbn;
	this.anoPublicacao = anoPublicacao;
	this.disponibilidade = disponibilidade;
	}

	// Getter e Setter do ID
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//GETTER (Permite ler) e SETTER (Permite alterar ) Quando usamos PRIVATE não precisa do set e get
	public String getTitulo() {    //aqui você só vai criar o Get, porque quando for chamar será em outra class
		return titulo;             //Estou criando um método chamado getTitulo que vai devolver o título deste livro. O RETURN, permite retornar
	}

	public void setTitulo(String titulo) {    //aqui você só vai criar o Set, porque quando for chamar será em outra class
	    this.titulo = titulo;                 // Void quer dizer que não precisa de Return
	}                                         //O this significa "Coloque o valor recebido no atributo titulo deste objeto."

	// Getter e Setter do autor
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	// Getter e Setter da editora
	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	// Getter e Setter do ISBN
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	// Getter e Setter do ano de publicação
	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	// Getter e Setter da disponibilidade
	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

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
