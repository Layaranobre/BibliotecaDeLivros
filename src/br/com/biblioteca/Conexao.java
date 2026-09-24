package br.com.biblioteca;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    public static Connection conectar() throws SQLException {

        Properties propriedades = new Properties();

        try {
            propriedades.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            throw new SQLException("Não foi possível carregar as configurações do banco.", e);
        }

        String url = propriedades.getProperty("db.url");
        String usuario = propriedades.getProperty("db.user");
        String senha = propriedades.getProperty("db.password");

        return DriverManager.getConnection(url, usuario, senha);
    }
}

//Alterado para não aparecer a senha no GitHub