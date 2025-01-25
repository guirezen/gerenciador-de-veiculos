-- Criação de tabelas
CREATE TABLE IF NOT EXISTS veiculos (
    id SERIAL PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    ano INT NOT NULL CHECK (ano >= 1886),
    preco DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('carro', 'moto'))
);

CREATE TABLE IF NOT EXISTS carros (
  veiculo_id INT PRIMARY KEY REFERENCES veiculos (id) ON DELETE CASCADE,
  quantidade_portas INT NOT NULL CHECK (quantidade_portas > 0),
  tipo_combustivel VARCHAR(10) NOT NULL CHECK (tipo_combustivel IN ('gasolina', 'etanol', 'diesel', 'flex'))
);

CREATE TABLE IF NOT EXISTS motos (
     veiculo_id INT PRIMARY KEY REFERENCES veiculos (id) ON DELETE CASCADE,
     cilindrada INT NOT NULL CHECK (cilindrada > 0)
);

-- Inserção de dados iniciais
INSERT INTO veiculos (modelo, fabricante, ano, preco, tipo)
VALUES ('Onix', 'Chevrolet', 2021, 80000.00, 'carro'), ('Civic', 'Honda', 2020, 90000.00, 'carro'),
    ('XRE 300', 'Honda', 2022, 25000.00, 'moto');

INSERT INTO carros (veiculo_id, quantidade_portas, tipo_combustivel)
VALUES (1, 4, 'flex'), (2, 4, 'gasolina');

INSERT INTO motos (veiculo_id, cilindrada) VALUES (3, 300);
