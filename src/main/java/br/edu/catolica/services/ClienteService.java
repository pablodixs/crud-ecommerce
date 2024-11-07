package br.edu.catolica.services;

import br.edu.catolica.config.DB;
import br.edu.catolica.daos.ClienteDAO;
import br.edu.catolica.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe de serviço do cliente que implementa o ClienteDAO
 *
 * @author Matheus, Pablo e Maria Fernanda
 * @version 1.0
 */
public class ClienteService implements ClienteDAO {
    /**
     * Método para cadastrar um cliente novo
     * @throws SQLException se houver algum erro durante a consulta ao banco de dados
     */
    @Override
    public void cadastrarCliente() {
        Connection conexao;
        PreparedStatement statement;

        Scanner sc = new Scanner(System.in);

        Cliente novoCliente = new Cliente();

        System.out.println("Insira o nome do cliente");
        novoCliente.setNome(sc.nextLine());

        System.out.println("Insira o e-mail");
        novoCliente.setEmail(sc.nextLine());

        System.out.println("Insira o telefone");
        novoCliente.setTelefone(sc.nextLine());

        novoCliente.setData_cadastro(new java.util.Date());

        try {
            /* conecta ao banco de dados */
            conexao = DB.conectar();
            conexao.createStatement();

            // matheus: cria a query que será usada pelo banco de dados
            statement = conexao.prepareStatement(
                    "INSERT INTO ecommerce.clientes(nome, email, telefone, data_cadastro) VALUES (?, ?, ?, ?)"
            );

            // matheus: define as variaveis do insert (values)
            statement.setString(1, novoCliente.getNome());
            statement.setString(2, novoCliente.getEmail());
            statement.setString(3, novoCliente.getTelefone());
            statement.setDate(4, new Date(novoCliente.getData_cadastro().getTime()));

            // matheus: executa a query
            statement.executeUpdate();

            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    // Maria: metodo para consultar cliente
    /**
     * Método para consultar um cliente
     * @param id o id do cliente
     * @return um cliente com o mesmo id dos parametros
     * @throws SQLException se houver algum erro na busca
     * */
    @Override
    public Cliente consultarCliente(int id) {
        Connection conexao;
        Statement comando;
        ResultSet resultado;

        Cliente cliente = null;

        try {
            // Maria: cria a conexao com o banco de dados
            conexao = DB.conectar();
            conexao.createStatement();
            comando = conexao.createStatement();

            // salva o que a query retornar do banco de dados na variavel resultado
            resultado = comando.executeQuery("SELECT cliente_id, nome, email, telefone, data_cadastro FROM ecommerce.clientes WHERE ecommerce.clientes.cliente_id = " + id);

            // loop para inserir os dados retornados do bd em um objeto java
            while(resultado.next()) {
                cliente = new Cliente();
                cliente.setCliente_ID(resultado.getInt("cliente_id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setData_cadastro(resultado.getDate("data_cadastro"));
            }

            // imprime os dados no console
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("E-mail: " + cliente.getEmail());
            System.out.println("Data de cadastro: " + cliente.getData_cadastro());

            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Método para listar todos os clientes
     * @return uma lista de clientes
     * @throws SQLException se houver algum erro de consulta
     * */
    @Override
    public List<Cliente> listarClientes() {
        Connection conexao;
        Statement comando;
        ResultSet resultado;

        // Inicializa uma lista vazia de clientes
        List<Cliente> clientes = new ArrayList<>();

        try {
            conexao = DB.conectar();
            comando = conexao.createStatement();

            // Query para selecionar todos os clientes e retorna os dados. Os dados são salvos na variável resultado
            resultado = comando.executeQuery("SELECT * FROM ecommerce.clientes");

            // Loop que instancia um novo objeto Cliente e adiciona a lista clientes
            while (resultado.next()) {
                Cliente cliente = new Cliente();

                cliente.setCliente_ID(resultado.getInt("cliente_id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setData_cadastro(resultado.getDate("data_cadastro"));

                // Adiciona o objeto criado na lista
                clientes.add(cliente);
            }

            // Loop que imprime todos os clientes no console
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getCliente_ID());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Telefone: " + cliente.getTelefone());
                System.out.println("E-mail: " + cliente.getEmail());
                System.out.println("Data de cadastro: " + cliente.getData_cadastro());
                System.out.println();
            }

            return clientes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    /**
     * Método para atualizar um cliente
     * @throws SQLException se houver algum erro na consulta ou atualização
     * */
    @Override
    public void atualizarCliente() {
        Connection conexao;
        PreparedStatement statement;

        try {
            conexao = DB.conectar();
            conexao.createStatement();

            Cliente updateCliente = new Cliente();

            // Requisita os novos dados pelo console
            Scanner sc = new Scanner(System.in);

            System.out.print("Insira o id do cliente: ");
            updateCliente.setCliente_ID(sc.nextInt());

            System.out.println("Insira o nome do cliente");
            updateCliente.setNome(sc.next());

            System.out.println("Insira o e-mail");
            updateCliente.setEmail(sc.next());

            System.out.println("Insira o telefone");
            updateCliente.setTelefone(sc.next());

            // Cria a query de UPDATE
            statement = conexao.prepareStatement(
                    "UPDATE ecommerce.clientes SET nome = ?, email = ?, telefone = ? WHERE ecommerce.clientes.cliente_id = ?"
            );

            // Substitui os "?" pelos valores capturados pelo scanner
            statement.setString(1, updateCliente.getNome());
            statement.setString(2, updateCliente.getEmail());
            statement.setString(3, updateCliente.getTelefone());
            statement.setInt(4, updateCliente.getCliente_ID());

            // Executa a query
            statement.executeUpdate();
            System.out.println("Cadastro do cliente atualizado com sucesso!");
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    /**
     * Método para excluir um cliente
     * @param id id do cliente a ser excluído
     * @throws SQLException se houver algum erro na consulta ou exclusão
     * */
    @Override
    public void excluirCliente(int id) {
        Connection conexao;
        PreparedStatement statement;

        try {
            conexao = DB.conectar();
            conexao.createStatement();

            // query para apagar o registro do banco de dados
            statement = conexao.prepareStatement("DELETE FROM ecommerce.clientes WHERE ecommerce.clientes.cliente_id = ?");

            // Define o id
            statement.setInt(1, id);

            // Executa a query
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
