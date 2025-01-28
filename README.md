# Gerenciador de Veículos

Este é o sistema completo **Gerenciador de Veículos**, composto por um backend e um frontend integrados. O projeto tem como objetivo gerenciar a frota de veículos de uma empresa, permitindo o cadastro, edição, exclusão e visualização de veículos.

---

## **Backend**

### **Tecnologias Utilizadas**
- **Java 17**
- **Spark Java**
- **PostgreSQL** (Banco de dados)
- **Docker** e **Docker Compose**
- **Gson** (para serialização/deserialização JSON)
- **JUnit 5** (para testes)

### **Configuração do Backend**

#### **1. Clonar o Repositório**
```bash
git clone https://github.com/guirezen/gerenciador-de-veiculos.git
cd gerendiador-de-veiculos
```

#### **2. Configuração do Banco de Dados**
##### Utilizando Docker Compose:
1. Certifique-se de ter o Docker e o Docker Compose instalados.
2. Execute o seguinte comando na raiz do projeto:
   ```bash
   docker-compose up --build
   ```
3. O PostgreSQL será iniciado com as seguintes credenciais:
   - **Usuário**: `postgres`
   - **Senha**: `root`
   - **Banco de dados**: `gerenciador_veiculos`

4. Os scripts SQL de inicialização (criação de tabelas e inserção de dados iniciais) serão executados automaticamente.

#### **3. Configurar o Backend**
1. Abra o projeto em sua IDE de preferência (recomendado: IntelliJ IDEA).
2. Certifique-se de que o arquivo **`DatabaseConnection.java`** está configurado com as credenciais do banco:
   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/gerenciador_veiculos";
   private static final String USER = "postgres";
   private static final String PASSWORD = "root";
   ```
3. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```
4. Certifique-se de configurar a variável **ENV** para o ambiente correto:
   - **`production`** para execução normal.
   - **`test`** para rodar os testes automatizados.

#### **4. Executar o Servidor**
1. Execute a classe principal **`AppServer`**:
   ```bash
   java -cp target/<nome-do-jar>.jar com.desafio.gerenciador.AppServer
   ```
2. O servidor será iniciado na porta **8080**.

### **Endpoints Disponíveis**

#### **Base URL**
```
http://localhost:8080
```

#### **1. Veículos**
- **Listar Todos os Veículos**:
  ```
  GET /veiculos
  ```
  **Resposta:**
  ```json
  [
    {
      "id": 1,
      "modelo": "Onix Plus",
      "fabricante": "Chevrolet",
      "ano": 2023,
      "preco": 85000.0,
      "tipo": "carro",
      "quantidadePortas": 4,
      "tipoCombustivel": "flex"
    },
    {
      "id": 2,
      "modelo": "CB 500F",
      "fabricante": "Honda",
      "ano": 2023,
      "preco": 32000.0,
      "tipo": "moto",
      "cilindrada": 500
    }
  ]
  ```

- **Cadastrar Veículo**:
  ```
  POST /veiculos
  ```
  **Body (JSON):**
  ```json
  {
    "modelo": "Onix Plus",
    "fabricante": "Chevrolet",
    "ano": 2023,
    "preco": 85000.0,
    "tipo": "carro",
    "quantidadePortas": 4,
    "tipoCombustivel": "flex"
  }
  ```

- **Atualizar Veículo**:
  ```
  PUT /veiculos/:id
  ```
  **Body (JSON):**
  ```json
  {
    "modelo": "Onix Plus Turbo",
    "fabricante": "Chevrolet",
    "ano": 2024,
    "preco": 87000.0,
    "tipo": "carro",
    "quantidadePortas": 4,
    "tipoCombustivel": "gasolina"
  }
  ```

- **Remover Veículo**:
  ```
  DELETE /veiculos/:id
  ```

- **Buscar Veículos por Filtro**:
  ```
  GET /veiculos/filtro?searchTerm=Onix
  ```
  **Parâmetros de Query:**
  - `searchTerm` (opcional): Termo para buscar por modelo, tipo ou ano.

### **Testes Automatizados no Backend**
1. Para executar os testes, utilize o Maven:
   ```bash
   mvn test
   ```
2. Os testes cobrem:
   - Cadastro e atualização de veículos, carros e motos.
   - Validações de atributos gerais e específicos.
   - Consultas por filtros e listagens.

---

## **Frontend**

### **Tecnologias Utilizadas**
- React
- Axios
- Material-UI

### **Configuração do Frontend**

#### **1. Clonar o Repositório**
```bash
git clone https://github.com/usuario/gerenciador-de-veiculos.git
```

#### **2. Navegar para o Diretório do Frontend**
```bash
cd gerenciador-de-veiculos/frontend
```

#### **3. Instalar Dependências**
```bash
npm install
```

#### **4. Iniciar a Aplicação**
Para iniciar a aplicação, execute:
```bash
npm run dev
```
A aplicação estará disponível em `http://localhost:5173`.

---

## **Padrões Seguidos**
- **Clean Code**: Segregação clara de responsabilidades entre backend e frontend.
- **CORS**: Backend configurado para permitir integração com o frontend.

---

## **Contribuições**
Sinta-se à vontade para abrir pull requests ou relatar problemas no repositório.

