const API_LIVROS = "https://biblioteca-de-livros-8w5t.onrender.com/livros";

const formLivro = document.getElementById("form-livro");
const tituloFormularioLivro = document.getElementById("titulo-formulario-livro");
const inputId = document.getElementById("input-id");
const inputTitulo = document.getElementById("input-titulo");
const inputAutor = document.getElementById("input-autor");
const inputEditora = document.getElementById("input-editora");
const inputIsbn = document.getElementById("input-isbn");
const inputAnoPublicacao = document.getElementById("input-ano-publicacao");
const inputDisponibilidade = document.getElementById("input-disponibilidade");
const btnSalvarLivro = document.getElementById("btn-salvar-livro");
const btnCancelarEdicao = document.getElementById("btn-cancelar-edicao");

const formBuscaLivro = document.getElementById("form-busca-livro");
const inputBuscaTitulo = document.getElementById("input-busca-titulo");
const btnLimparBusca = document.getElementById("btn-limpar-busca");
const listaLivros = document.getElementById("lista-livros");
const mensagemLivros = document.getElementById("mensagem-livros");

const totalLivros = document.getElementById("total-livros");
const totalDisponiveis = document.getElementById("total-disponiveis");
const totalIndisponiveis = document.getElementById("total-indisponiveis");
const avisoLivro = document.getElementById("aviso-livro");

let livros = [];
let temporizadorAviso = null;


// =========================
// MENSAGEM DE AVISO
// =========================

function mostrarAviso(texto, tipo = "sucesso") {
  avisoLivro.textContent = texto;
  avisoLivro.className = "aviso-livro aviso-livro--" + tipo;
  avisoLivro.hidden = false;

  clearTimeout(temporizadorAviso);

  temporizadorAviso = setTimeout(() => {
    avisoLivro.hidden = true;
  }, 3000);
}


// =========================
// REQUISIÇÃO PARA A API
// =========================

async function requisicao(url, opcoes = {}) {
  const resposta = await fetch(url, {
    headers: {
      "Content-Type": "application/json"
    },
    ...opcoes,
  });

  if (!resposta.ok) {
    throw new Error("Erro " + resposta.status);
  }

  const texto = await resposta.text();

  if (!texto) {
    return null;
  }

  try {
    return JSON.parse(texto);
  } catch {
    return texto;
  }
}


// =========================
// CARREGAR LIVROS
// =========================

async function carregarLivros() {
  try {
    livros = await requisicao(API_LIVROS);

    renderizarLivros(livros);
  } catch (erro) {
    console.error(erro);

    mostrarAviso(
      "Não foi possível carregar os livros. A API está rodando?",
      "erro"
    );

    renderizarLivros([]);
  }
}


// =========================
// BUSCAR LIVRO
// =========================

async function buscarLivro(titulo) {
  try {
    const livro = await requisicao(
      API_LIVROS + "/buscar?titulo=" + encodeURIComponent(titulo)
    );

    if (livro) {
      renderizarLivros([livro]);
    } else {
      renderizarLivros([]);

      mostrarAviso("Livro não encontrado.", "erro");
    }

  } catch (erro) {
    console.error(erro);

    renderizarLivros([]);

    mostrarAviso("Livro não encontrado.", "erro");
  }
}


// =========================
// CADASTRAR LIVRO
// =========================

async function cadastrarLivro(livro) {
  await requisicao(API_LIVROS, {
    method: "POST",
    body: JSON.stringify(livro)
  });
}


// =========================
// ATUALIZAR LIVRO
// =========================

async function atualizarLivro(id, livro) {
  await requisicao(API_LIVROS + "/" + id, {
    method: "PUT",
    body: JSON.stringify(livro)
  });
}


// =========================
// REMOVER LIVRO
// =========================

async function removerLivro(id) {
  await requisicao(API_LIVROS + "/" + id, {
    method: "DELETE"
  });
}


// =========================
// CRIAR CÉLULA DA TABELA
// =========================

function criarCelula(texto, classe) {
  const td = document.createElement("td");

  td.textContent = texto;

  if (classe) {
    td.className = classe;
  }

  return td;
}


// =========================
// CRIAR BOTÃO
// =========================

function criarBotao(texto, classes, aoClicar) {
  const botao = document.createElement("button");

  botao.type = "button";
  botao.textContent = texto;
  botao.className =
    "botao-livro botao-livro--pequeno " + classes;

  botao.addEventListener("click", aoClicar);

  return botao;
}


// =========================
// RENDERIZAR LIVROS
// =========================

function renderizarLivros(lista) {
  listaLivros.innerHTML = "";

  lista.forEach((livro) => {

    const linha = document.createElement("tr");

    linha.className = "livro-linha";
    linha.dataset.id = livro.id;


    linha.appendChild(
      criarCelula(livro.titulo, "livro-titulo")
    );

    linha.appendChild(
      criarCelula(livro.autor, "livro-autor")
    );

    linha.appendChild(
      criarCelula(livro.editora, "livro-editora")
    );

    linha.appendChild(
      criarCelula(livro.isbn, "livro-isbn")
    );

    linha.appendChild(
      criarCelula(livro.anoPublicacao, "livro-ano")
    );


    // Situação

    const celulaSituacao = document.createElement("td");

    const situacao = document.createElement("span");

    situacao.className =
      "livro-situacao " +
      (
        livro.disponibilidade
          ? "livro-situacao--disponivel"
          : "livro-situacao--indisponivel"
      );

    situacao.textContent =
      livro.disponibilidade
        ? "Disponível"
        : "Indisponível";

    celulaSituacao.appendChild(situacao);

    linha.appendChild(celulaSituacao);


    // Ações

    const celulaAcoes = document.createElement("td");

    const acoes = document.createElement("div");

    acoes.className = "livro-acoes";


    // Editar

    acoes.appendChild(
      criarBotao(
        "Editar",
        "botao-livro--secundario",
        () => iniciarEdicao(livro)
      )
    );


    // Alterar disponibilidade

    acoes.appendChild(
      criarBotao(
        livro.disponibilidade
          ? "Marcar indisponível"
          : "Marcar disponível",

        "botao-livro--secundario",

        () => alternarDisponibilidade(livro)
      )
    );


    // Remover

    acoes.appendChild(
      criarBotao(
        "Remover",
        "botao-livro--perigo",
        () => confirmarRemocao(livro)
      )
    );


    celulaAcoes.appendChild(acoes);

    linha.appendChild(celulaAcoes);

    listaLivros.appendChild(linha);
  });


  mensagemLivros.hidden = lista.length > 0;

  atualizarResumo();
}


// =========================
// ATUALIZAR RESUMO
// =========================

function atualizarResumo() {
  const disponiveis =
    livros.filter((livro) => livro.disponibilidade).length;

  totalLivros.textContent = livros.length;

  totalDisponiveis.textContent = disponiveis;

  totalIndisponiveis.textContent =
    livros.length - disponiveis;
}


// =========================
// LER FORMULÁRIO
// =========================

function lerFormulario() {
  return {
    titulo: inputTitulo.value.trim(),

    autor: inputAutor.value.trim(),

    editora: inputEditora.value.trim(),

    isbn: inputIsbn.value.trim(),

    anoPublicacao: inputAnoPublicacao.value.trim(),

    disponibilidade: inputDisponibilidade.checked
  };
}


// =========================
// LIMPAR FORMULÁRIO
// =========================

function limparFormulario() {
  formLivro.reset();

  inputId.value = "";

  inputDisponibilidade.checked = true;

  tituloFormularioLivro.textContent = "Cadastrar livro";

  btnSalvarLivro.textContent = "Cadastrar livro";

  btnCancelarEdicao.hidden = true;
}


// =========================
// INICIAR EDIÇÃO
// =========================

function iniciarEdicao(livro) {
  inputId.value = livro.id;

  inputTitulo.value = livro.titulo;

  inputAutor.value = livro.autor;

  inputEditora.value = livro.editora;

  inputIsbn.value = livro.isbn;

  inputAnoPublicacao.value = livro.anoPublicacao;

  inputDisponibilidade.checked = livro.disponibilidade;

  tituloFormularioLivro.textContent = "Alterar livro";

  btnSalvarLivro.textContent = "Salvar alterações";

  btnCancelarEdicao.hidden = false;

  inputTitulo.focus();

  window.scrollTo({
    top: 0,
    behavior: "smooth"
  });
}


// =========================
// CADASTRAR / EDITAR
// =========================

formLivro.addEventListener("submit", async (evento) => {

  evento.preventDefault();

  const livro = lerFormulario();

  const id = inputId.value;

  try {

    if (id) {

      await atualizarLivro(id, livro);

      mostrarAviso(
        "Livro atualizado com sucesso!"
      );

    } else {

      await cadastrarLivro(livro);

      mostrarAviso(
        "Livro cadastrado com sucesso!"
      );
    }

    limparFormulario();

    await carregarLivros();

  } catch (erro) {

    console.error(erro);

    mostrarAviso(
      "Não foi possível salvar o livro.",
      "erro"
    );
  }
});


// =========================
// CANCELAR EDIÇÃO
// =========================

btnCancelarEdicao.addEventListener(
  "click",
  limparFormulario
);


// =========================
// ALTERAR DISPONIBILIDADE
// =========================

async function alternarDisponibilidade(livro) {

  try {

    await atualizarLivro(
      livro.id,
      {
        ...livro,
        disponibilidade: !livro.disponibilidade
      }
    );

    await carregarLivros();

    mostrarAviso(
      "Situação do livro atualizada."
    );

  } catch (erro) {

    console.error(erro);

    mostrarAviso(
      "Não foi possível alterar a situação.",
      "erro"
    );
  }
}


// =========================
// REMOVER LIVRO
// =========================

async function confirmarRemocao(livro) {

  if (
    !confirm(
      'Deseja remover o livro "' +
      livro.titulo +
      '"?'
    )
  ) {
    return;
  }

  try {

    await removerLivro(livro.id);

    mostrarAviso(
      "Livro removido com sucesso!"
    );


    if (
      inputId.value &&
      Number(inputId.value) === livro.id
    ) {
      limparFormulario();
    }

    await carregarLivros();

  } catch (erro) {

    console.error(erro);

    mostrarAviso(
      "Não foi possível remover o livro.",
      "erro"
    );
  }
}


// =========================
// BUSCAR LIVRO
// =========================

formBuscaLivro.addEventListener(
  "submit",
  (evento) => {

    evento.preventDefault();

    const titulo =
      inputBuscaTitulo.value.trim();

    if (titulo === "") {

      renderizarLivros(livros);

      return;
    }

    buscarLivro(titulo);
  }
);


// =========================
// LIMPAR BUSCA
// =========================

btnLimparBusca.addEventListener(
  "click",
  () => {

    inputBuscaTitulo.value = "";

    renderizarLivros(livros);
  }
);


// =========================
// INICIAR
// =========================

carregarLivros();