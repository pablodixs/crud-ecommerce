package br.edu.catolica.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Classe responsável pela configuração conexão com o banco de dados
 * @author Pablo
 * @version 1.0
 * */
public class DB {
    private static Connection conexao = null;

    /**
     * Método que é responsavel pela conexão do jdbc com o banco de dados
     * @throws DBException se houver algum erro ao carregar as propriedades
     * */
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

    /**
     * Método estático para carregar o arquivo de db.properties
     * @throws DBException se houver algum problema de I/O
     * */
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