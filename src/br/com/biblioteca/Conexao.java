package br.com.biblioteca;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    public static Connection conectar() throws SQLException {

        String url = System.getenv("DB_URL");
        String usuario = System.getenv("DB_USER");
        String senha = System.getenv("DB_PASSWORD");

        if (url == null || usuario == null || senha == null) {

            Properties propriedades = new Properties();

            try {
                propriedades.load(new FileInputStream("application.properties"));
            } catch (IOException e) {
                throw new SQLException(
                        "Não foi possível carregar as configurações do banco.",
                        e
                );
            }

            url = propriedades.getProperty("db.url");
            usuario = propriedades.getProperty("db.user");
            senha = propriedades.getProperty("db.password");
        }

        return DriverManager.getConnection(url, usuario, senha);
    }

}

//Alterado para não aparecer a senha no GitHub