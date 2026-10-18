//Teste de Conexão para o MYSQL

package br.com.biblioteca;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try (Connection conexao = Conexao.conectar()) {

            System.out.println("Conectado ao MySQL!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
