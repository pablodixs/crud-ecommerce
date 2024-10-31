package br.edu.catolica.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conexao = null;

    // matheus: método que é responsavel pela conexão do jdbc com o banco de dados
    public static Connection conectar() {
        if(conexao == null) {
            try {
                // Busca os dados do banco de dados contidos no arquivo db.properties
                Properties props = carregarPropiedades();
                String url = props.getProperty("jdbc.url");

                conexao = DriverManager.getConnection(url, props);
            } catch (Exception e) {
                throw new DBException(e.getMessage());
            }
        }

        return conexao;
    }

    // Maria: método estático para carregar o arquivo de db.properties
    private static Properties carregarPropiedades() {
        try(FileInputStream fs = new FileInputStream("src/main/resources/db.properties")) {
            Properties prop = new Properties();
            prop.load(fs);

            return prop;
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }
}