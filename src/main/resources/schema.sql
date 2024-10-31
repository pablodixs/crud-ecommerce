CREATE DATABASE ECOMMERCE;
USE ECOMMERCE;
CREATE TABLE Clientes (
    Cliente_ID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(100),
    Email VARCHAR(100) UNIQUE,
    Telefone VARCHAR(20),
    Data_Cadastro DATE
);

CREATE TABLE Enderecos (
    Endereco_ID INT PRIMARY KEY AUTO_INCREMENT,
    Cliente_ID INT,
    Rua VARCHAR(150),
    Cidade VARCHAR(100),
    Estado VARCHAR(50),
    CEP VARCHAR(20),
    FOREIGN KEY (Cliente_ID) REFERENCES Clientes(Cliente_ID)
);

CREATE TABLE Categorias (
    Categoria_ID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(100),
    Descricao VARCHAR(100)
);

CREATE TABLE Fornecedores (
    Fornecedor_ID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(100),
    Telefone VARCHAR(20),
    Email VARCHAR(100)
);

CREATE TABLE Produtos (
    Produto_ID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(100),
    Descricao VARCHAR(100),
    Preco DECIMAL(10, 2),
    Estoque INT,
    Categoria_ID INT,
    Fornecedor_ID INT,
    FOREIGN KEY (Categoria_ID) REFERENCES Categorias(Categoria_ID),
    FOREIGN KEY (Fornecedor_ID) REFERENCES Fornecedores(Fornecedor_ID)
);

CREATE TABLE Vendas (
    Venda_ID INT PRIMARY KEY AUTO_INCREMENT,
    Cliente_ID INT,
    Data_Venda DATE,
    Status_ID INT,
    FOREIGN KEY (Cliente_ID) REFERENCES Clientes(Cliente_ID),
    FOREIGN KEY (Status_ID) REFERENCES Status_Pedido(Status_ID)
);

CREATE TABLE Itens_Venda (
    Item_ID INT PRIMARY KEY AUTO_INCREMENT,
    Venda_ID INT,
    Produto_ID INT,
    Quantidade INT,
    Preco_Unitario DECIMAL(10, 2),
    FOREIGN KEY (Venda_ID) REFERENCES Vendas(Venda_ID),
    FOREIGN KEY (Produto_ID) REFERENCES Produtos(Produto_ID)
);

CREATE TABLE Pagamentos (
    Pagamento_ID INT PRIMARY KEY AUTO_INCREMENT,
    Venda_ID INT,
    Metodo_Pagamento VARCHAR(50),
    Valor DECIMAL(10, 2),
    Data_Pagamento DATE,
    FOREIGN KEY (Venda_ID) REFERENCES Vendas(Venda_ID)
);

CREATE TABLE Status_Pedido (
    Status_ID INT PRIMARY KEY AUTO_INCREMENT,
    Descricao VARCHAR(50)
);

CREATE TABLE Carrinho (
    Carrinho_ID INT PRIMARY KEY AUTO_INCREMENT,
    Cliente_ID INT,
    Produto_ID INT,
    Quantidade INT,
    FOREIGN KEY (Cliente_ID) REFERENCES Clientes(Cliente_ID),
    FOREIGN KEY (Produto_ID) REFERENCES Produtos(Produto_ID)
);