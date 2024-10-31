package br.edu.catolica.models;

import java.util.Date;

//matheus: modelo que representa o cliente
public class Cliente {
    // atributos do cliente
    private int cliente_ID;
    private String nome;
    private String email;
    private String telefone;
    private Date data_cadastro;

    // construtor do objeto
    public Cliente() {}

    // getters e setters
    public int getCliente_ID() {
        return cliente_ID;
    }

    public void setCliente_ID(int cliente_ID) {
        this.cliente_ID = cliente_ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
}
