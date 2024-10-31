package br.edu.catolica.config;

public class DBException extends RuntimeException{
    public DBException(String msg){
        super(msg);
    }
}