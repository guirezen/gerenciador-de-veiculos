-- Usar o esquema padrão
SET search_path TO gerenciador_veiculos;

TRUNCATE TABLE veiculos, carros, motos RESTART IDENTITY;

-- Inserção de veículos genéricos
INSERT INTO veiculos (modelo, fabricante, ano, preco, tipo)
VALUES
    ('Onix', 'Chevrolet', 2021, 50000.00, 'carro'),
    ('Civic', 'Honda', 2020, 80000.00, 'carro'),
    ('XRE 300', 'Honda', 2022, 20000.00, 'moto');

-- Inserção de dados específicos de carros
INSERT INTO carros (veiculo_id, quantidade_portas, tipo_combustivel)
VALUES
    (1, 4, 'flex'),
    (2, 4, 'gasolina');

-- Inserção de dados específicos de motos
INSERT INTO motos (veiculo_id, cilindrada)
VALUES
    (3, 300);