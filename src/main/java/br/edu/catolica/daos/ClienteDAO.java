package br.edu.catolica.daos;

import br.edu.catolica.models.Cliente;

import java.util.List;

// (Pablo) => Interface do Data Access Object
public interface ClienteDAO {
    void cadastrarCliente();
    Cliente consultarCliente(int id);
    List<Cliente> listarClientes();
    void atualizarCliente();
    void excluirCliente(int id);
}
