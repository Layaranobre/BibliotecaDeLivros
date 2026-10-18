package br.com.biblioteca.controller;

import br.com.biblioteca.Livro;
import br.com.biblioteca.LivroDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroDAO livroDAO = new LivroDAO();


    // =========================================================
    // LISTAR TODOS
    // GET /livros
    // =========================================================

    @GetMapping
    public List<Livro> listarLivros() {
        return livroDAO.listar();
    }


    // =========================================================
    // BUSCAR POR ID
    // GET /livros/1
    // =========================================================

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id) {

        return livroDAO.buscarPorId(id);
    }


    // =========================================================
    // CADASTRAR
    // POST /livros
    // =========================================================

    @PostMapping
    public String cadastrarLivro(@RequestBody Livro livro) {

        livroDAO.cadastrar(livro);

        return "Livro cadastrado com sucesso!";
    }


    // =========================================================
    // ATUALIZAR
    // PUT /livros/1
    // =========================================================

    @PutMapping("/{id}")
    public String atualizarLivro(
            @PathVariable Long id,
            @RequestBody Livro livro) {

        boolean atualizado = livroDAO.atualizar(id, livro);

        if (atualizado) {
            return "Livro atualizado com sucesso!";
        }

        return "Livro não encontrado!";
    }


    // =========================================================
    // EXCLUIR
    // DELETE /livros/1
    // =========================================================

    @DeleteMapping("/{id}")
    public String excluirLivro(@PathVariable Long id) {

        boolean removido = livroDAO.remover(id);

        if (removido) {
            return "Livro removido com sucesso!";
        }

        return "Livro não encontrado!";
    }
}