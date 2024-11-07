package br.edu.catolica.config;

/**
 * Exception personalizada. Classe que extende uma exception.
 * @version 1.0
 * */
public class DBException extends RuntimeException{
    /**
     * Construtor da exception
     * @param msg Mensagem a ser exibida na exception
     * */
    public DBException(String msg){
        super(msg);
    }
}