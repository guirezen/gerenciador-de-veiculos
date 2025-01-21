-- Criação do esquema do banco de dados
CREATE DATABASE gerenciamento_veiculos;

-- Tabela Veiculo
CREATE TABLE veiculo (
     id SERIAL PRIMARY KEY,
     modelo VARCHAR(100) NOT NULL,
     fabricante VARCHAR(100) NOT NULL,
     ano INT NOT NULL,
     preco DECIMAL(10, 2) NOT NULL,
     tipo_veiculo VARCHAR(10) CHECK (tipo_veiculo IN ('carro', 'moto')) NOT NULL
);

-- Tabela Carro
CREATE TABLE carro (
   id INT PRIMARY KEY,
   quantidade_portas INT NOT NULL,
   tipo_combustivel VARCHAR(10) CHECK (tipo_combustivel IN ('gasolina', 'etanol', 'diesel', 'flex')) NOT NULL,
   FOREIGN KEY (id) REFERENCES veiculo(id) ON DELETE CASCADE
);

-- Tabela Moto
CREATE TABLE moto (
    id INT PRIMARY KEY,
    cilindrada INT NOT NULL,
    FOREIGN KEY (id) REFERENCES veiculo(id) ON DELETE CASCADE
);