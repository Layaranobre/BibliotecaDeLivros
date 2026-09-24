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

            comando.setString(1, livro.titulo);
            comando.setString(2, livro.autor);
            comando.setString(3, livro.editora);
            comando.setString(4, livro.isbn);
            comando.setString(5, livro.anoPublicacao);
            comando.setBoolean(6, livro.disponibilidade);

            comando.executeUpdate();

            System.out.println("Livro cadastrado no banco!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =========================================================
    // LISTAR LIVROS
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


//=========================================================
//BUSCAR LIVRO PELO TÍTULO
//=========================================================

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

         Livro livro = new Livro(
                 resultado.getString("titulo"),
                 resultado.getString("autor"),
                 resultado.getString("editora"),
                 resultado.getString("isbn"),
                 resultado.getString("ano_publicacao"),
                 resultado.getBoolean("disponibilidade")
         );

         return livro;
     }

 } catch (Exception e) {
     e.printStackTrace();
 }

 return null;
}

//=========================================================
//REMOVER LIVRO PELO TÍTULO
//=========================================================

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
}
