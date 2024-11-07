package br.edu.catolica.daos;

import br.edu.catolica.models.Cliente;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface do DAO Cliente
 * @author Matheus
 * @version 1.0
 * */
// (Pablo) => Interface do Data Access Object
public interface ClienteDAO {
    void cadastrarCliente();
    Cliente consultarCliente(int id);
    List<Cliente> listarClientes();
    void atualizarCliente();
    void excluirCliente(int id);
}
