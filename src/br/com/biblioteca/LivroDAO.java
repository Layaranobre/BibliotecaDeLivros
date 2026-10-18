package br.com.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

	// =========================================================
	// CADASTRAR LIVRO
	// =========================================================

	public void cadastrar(Livro livro) {

		String sql = "INSERT INTO livros "
				+ "(titulo, autor, editora, isbn, ano_publicacao, disponibilidade) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conexao = Conexao.conectar();
			 PreparedStatement comando = conexao.prepareStatement(sql)) {

			comando.setString(1, livro.getTitulo());
			comando.setString(2, livro.getAutor());
			comando.setString(3, livro.getEditora());
			comando.setString(4, livro.getIsbn());
			comando.setString(5, livro.getAnoPublicacao());
			comando.setBoolean(6, livro.isDisponibilidade());

			comando.executeUpdate();

			System.out.println("Livro cadastrado no banco!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// =========================================================
	// LISTAR TODOS OS LIVROS
	// =========================================================

	public List<Livro> listar() {

		List<Livro> livros = new ArrayList<>();

		String sql = "SELECT titulo, autor, editora, isbn, "
				+ "ano_publicacao, disponibilidade "
				+ "FROM livros";

		try (Connection conexao = Conexao.conectar();
			 PreparedStatement comando = conexao.prepareStatement(sql);
			 ResultSet resultado = comando.executeQuery()) {

			while (resultado.next()) {

				Livro livro = new Livro(
						resultado.getString("titulo"),
						resultado.getString("autor"),
						resultado.getString("editora"),
						resultado.getString("isbn"),
						resultado.getString("ano_publicacao"),
						resultado.getBoolean("disponibilidade")
				);

				livros.add(livro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return livros;
	}


	// =========================================================
	// BUSCAR LIVRO PELO TÍTULO
	// =========================================================

	public Livro buscarPorTitulo(String titulo) {

		String sql = "SELECT titulo, autor, editora, isbn, "
				+ "ano_publicacao, disponibilidade "
				+ "FROM livros "
				+ "WHERE titulo = ?";

		try (Connection conexao = Conexao.conectar();
			 PreparedStatement comando = conexao.prepareStatement(sql)) {

			comando.setString(1, titulo);

			ResultSet resultado = comando.executeQuery();

			if (resultado.next()) {

				return new Livro(
						resultado.getString("titulo"),
						resultado.getString("autor"),
						resultado.getString("editora"),
						resultado.getString("isbn"),
						resultado.getString("ano_publicacao"),
						resultado.getBoolean("disponibilidade")
				);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	// =========================================================
	// REMOVER LIVRO PELO TÍTULO
	// =========================================================

	public void removerPorTitulo(String titulo) {

		String sql = "DELETE FROM livros WHERE titulo = ?";

		try (Connection conexao = Conexao.conectar();
			 PreparedStatement comando = conexao.prepareStatement(sql)) {

			comando.setString(1, titulo);

			int linhasAfetadas = comando.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Livro removido do banco!");
			} else {
				System.out.println("Livro não encontrado no banco.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
// =========================================================
// BUSCAR LIVRO PELO ID
// =========================================================

	public Livro buscarPorId(Long id) {

		String sql = "SELECT id, titulo, autor, editora, isbn, "
				+ "ano_publicacao, disponibilidade "
				+ "FROM livros WHERE id = ?";

		try (Connection conexao = Conexao.conectar();
			 PreparedStatement comando = conexao.prepareStatement(sql)) {

			comando.setLong(1, id);

			ResultSet resultado = comando.executeQuery();

			if (resultado.next()) {

				Livro livro = new Livro(
						resultado.getString("titulo"),
						resultado.getString("autor"),
						resultado.getString("editora"),
						resultado.getString("isbn"),
						resultado.getString("ano_publicacao"),
						resultado.getBoolean("disponibilidade")
				);

				livro.setId(resultado.getLong("id"));

				return livro;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


// =========================================================
// ATUALIZAR LIVRO
// =========================================================

	public boolean atualizar(Long id, Livro livro) {

		String sql = "UPDATE livros SET "
				+ "titulo = ?, "
				+ "autor = ?, "
				+ "editora = ?, "
				+ "isbn = ?, "
				+ "ano_publicacao = ?, "
				+ "disponibilidade = ? "
				+ "WHERE id = ?";

		try (Connection conexao = Conexao.conectar();
			 PreparedStatement comando = conexao.prepareStatement(sql)) {

			comando.setString(1, livro.getTitulo());
			comando.setString(2, livro.getAutor());
			comando.setString(3, livro.getEditora());
			comando.setString(4, livro.getIsbn());
			comando.setString(5, livro.getAnoPublicacao());
			comando.setBoolean(6, livro.isDisponibilidade());
			comando.setLong(7, id);

			int linhasAfetadas = comando.executeUpdate();

			return linhasAfetadas > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


// =========================================================
// REMOVER LIVRO PELO ID
// =========================================================

	public boolean remover(Long id) {

		String sql = "DELETE FROM livros WHERE id = ?";

		try (Connection conexao = Conexao.conectar();
			 PreparedStatement comando = conexao.prepareStatement(sql)) {

			comando.setLong(1, id);

			int linhasAfetadas = comando.executeUpdate();

			return linhasAfetadas > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}