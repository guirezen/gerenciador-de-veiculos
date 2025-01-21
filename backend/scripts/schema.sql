-- Selecionar o esquema padrão (se necessário)
CREATE SCHEMA IF NOT EXISTS gerenciador_veiculos;

-- Usar o esquema padrão
SET search_path TO gerenciador_veiculos;

-- Tabela de veículos (atributos comuns)
CREATE TABLE IF NOT EXISTS veiculos (
    id SERIAL PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    ano INT NOT NULL CHECK (ano >= 1886), -- Ano mínimo do primeiro carro
    preco DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('carro', 'moto')) -- Tipo do veículo
);

-- Tabela de carros (atributos específicos)
CREATE TABLE IF NOT EXISTS carros (
  veiculo_id INT PRIMARY KEY REFERENCES veiculos (id) ON DELETE CASCADE,
  quantidade_portas INT NOT NULL CHECK (quantidade_portas > 0),
  tipo_combustivel VARCHAR(10) NOT NULL CHECK (tipo_combustivel IN ('gasolina', 'etanol', 'diesel', 'flex'))
);

-- Tabela de motos (atributos específicos)
CREATE TABLE IF NOT EXISTS motos (
     veiculo_id INT PRIMARY KEY REFERENCES veiculos (id) ON DELETE CASCADE,
     cilindrada INT NOT NULL CHECK (cilindrada > 0)
);