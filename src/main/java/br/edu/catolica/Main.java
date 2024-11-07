package br.edu.catolica;

import br.edu.catolica.services.ClienteService;

import java.util.Scanner;

/**
 * Método principal do programa
 *
 * @author Pablo, Matheus e Maria Fernanda
 * @version 1.0
 * */
public class Main {
    public static void main(String[] args) {
        // Pablo => Instancia o clienteService
        ClienteService clienteService = new ClienteService();

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        //Maria: Loop para o menu
        while (option != 6) {
            System.out.println("############### E-COMMERCE ###############");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Atualizar cliente");
            System.out.println("3. Listar clientes");
            System.out.println("4. Buscar cliente");
            System.out.println("5. Excluir cliente");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();

            // Switch com as opções do menu
            switch (option) {
                case 1:
                    System.out.println();
                    System.out.println("############### CADASTRAR CLIENTE ###############");
                    clienteService.cadastrarCliente();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("############### ATUALIZAR CLIENTE ###############");
                    clienteService.atualizarCliente();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("############### LISTAR CLIENTES ###############");
                    clienteService.listarClientes();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("############### BUSCAR CLIENTES ###############");
                    System.out.println("Insira o ID para busca: ");
                    clienteService.consultarCliente(scanner.nextInt());
                    break;
                case 5:
                    System.out.println();
                    System.out.println("############### EXCLUIR CLIENTE ###############");
                    System.out.println("Insira o ID do cliente: ");
                    clienteService.excluirCliente(scanner.nextInt());
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }

        scanner.close();
    }
}