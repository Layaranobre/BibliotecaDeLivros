<h1 align="center">📚 Biblioteca de Livros</h1>

<p align="center">
  Cadastre, busque, altere e remova livros do seu acervo.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/status-em%20andamento-yellow?style=for-the-badge" alt="Status: em andamento" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17" />
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3" />
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript" />
</p>

---

> 🚧 **Projeto em andamento.** Novas funcionalidades estão sendo desenvolvidas
> e algumas partes ainda podem mudar.

## 📖 Sobre o projeto

A **Biblioteca de Livros** é uma aplicação web para gerenciar um acervo de livros.
O front-end (HTML, CSS e JavaScript) conversa com uma **API REST em Java com Spring Boot**,
que guarda os dados em um banco **MySQL na nuvem**. O back-end é empacotado com
**Docker** e o front-end é publicado automaticamente no **GitHub Pages**.

## ✨ Funcionalidades

- ➕ Cadastrar livros (título, autor, editora, ISBN, ano de publicação e disponibilidade)
- 📋 Listar todos os livros em uma tabela
- 🔎 Buscar livro pelo título
- ✏️ Editar livros já cadastrados
- 🗑️ Excluir livros
- 📊 Painel de resumo: total de livros, disponíveis e indisponíveis
- 💻 Versão em terminal (menu no console) para cadastrar, listar, buscar e remover

## 🔮 Em desenvolvimento

- [ ] Tratamento de erros e validações mais completos na API
- [ ] Cadastro para cada usuário e Autenticação de usuários
- [ ] Busca por autor e filtros por disponibilidade
- [ ] Livro completo, imagens e textos.
- [ ] Inclução de outras APIs

## 🛠️ Tecnologias

| Camada | Tecnologias |
| --- | --- |
| **Front-end** | HTML5, CSS3, JavaScript |
| **Back-end** | Java 17, Spring Boot (Spring Web e Spring Data JPA), JDBC |
| **Banco de dados** | MySQL (nuvem) |
| **Infraestrutura** | Docker, Maven, GitHub Actions, GitHub Pages |

## 🔌 Endpoints da API

Base: `/livros`

| Método | Rota | Descrição |
| --- | --- | --- |
| `GET` | `/livros` | Lista todos os livros |
| `GET` | `/livros/buscar?titulo=...` | Busca um livro pelo título |
| `GET` | `/livros/{id}` | Busca um livro pelo ID |
| `POST` | `/livros` | Cadastra um novo livro |
| `PUT` | `/livros/{id}` | Atualiza um livro |
| `DELETE` | `/livros/{id}` | Remove um livro |


## ☁️ Publicação

- **Front-end:** publicado automaticamente no GitHub Pages por um workflow do
  GitHub Actions a cada push na branch `main`.
- **Back-end:** roda em container Docker e se conecta ao MySQL na nuvem.

## 📂 Estrutura do projeto

```
BibliotecaDeLivros/
├── .github/workflows/
│   └── pages.yml              # publica o front-end no GitHub Pages
├── front/
│   ├── index.html
│   ├── style.css
│   └── script.js
├── src/br/com/biblioteca/
│   ├── controller/
│   │   └── LivroController.java   # endpoints REST
│   ├── BibliotecaApplication.java # inicia o Spring Boot
│   ├── Livro.java                 # entidade
│   ├── LivroDAO.java              # acesso ao MySQL (JDBC)
│   ├── Conexao.java               # conexão com o banco
│   ├── Menu.java / Main.java      # versão em terminal
│   └── Biblioteca.java
├── Dockerfile
├── pom.xml
└── requests.http
```

## 👩‍💻 Autora

Feito com 💜 por **Layara Nobre**

[![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white)](https://github.com/Layaranobre)
