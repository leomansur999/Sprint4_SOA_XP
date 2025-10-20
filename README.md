# 💼 XP Smart Advisor

## 🧠 Sobre o Projeto

O **XP Smart Advisor** é um **assessor virtual inteligente de investimentos**, desenvolvido em **Java Spring Boot**, que permite o **cadastro e gerenciamento de clientes**, além de **gerar recomendações de carteiras de investimento** com base no perfil do investidor.

O sistema possui **autenticação JWT (JSON Web Token)**, controle de acesso seguro e integração com banco de dados MySQL.  
Foi desenvolvido como projeto acadêmico para demonstrar **boas práticas de arquitetura, segurança e DevSecOps.**

---

## 🏗️ Tecnologias Utilizadas

| Categoria | Tecnologia |
|------------|-------------|
| Linguagem | Java 17 / 21 |
| Framework | Spring Boot 3 |
| Segurança | Spring Security + JWT |
| Banco de Dados | MySQL 8 |
| ORM | Hibernate / JPA |
| Testes | JUnit 5 + MockMvc |
| Documentação | OpenAPI / Swagger UI |
| Build | Maven 3 |
| IDE recomendada | IntelliJ IDEA |

---

## ⚙️ Estrutura do Projeto

```
src/
 ├── main/java/br/com/fiap/Sprint4_SOA_XP
 │   ├── config/            → Configurações de segurança e JWT
 │   ├── controller/        → Controladores REST (Auth, Cliente, Recomendação)
 │   ├── dto/               → Objetos de transferência de dados (DTOs)
 │   ├── exception/         → Tratamento global de exceções
 │   ├── modelo/            → Entidades JPA (Cliente, User, Role, etc.)
 │   ├── repository/        → Interfaces do Spring Data JPA
 │   ├── servico/           → Interfaces de serviço
 │   └── servico/impl/      → Implementações dos serviços
 └── test/java/...          → Testes de integração e unidade
```

---

## 🔐 Autenticação e Segurança

O sistema utiliza **JWT** para autenticação.  
Fluxo básico:

1. `POST /auth/register` — Cria um novo usuário.
2. `POST /auth/login` — Autentica o usuário e retorna um **token JWT**.
3. As demais rotas exigem o header:
   ```
   Authorization: Bearer <seu_token_jwt>
   ```

O token é validado pelo filtro `JwtAuthFilter` antes de permitir o acesso às rotas protegidas.

---

## 📡 Endpoints Principais

### 🔑 Autenticação (`/auth`)
| Método | Endpoint | Descrição | Público |
|--------|-----------|------------|---------|
| POST | `/auth/register` | Cria um novo usuário | ✅ |
| POST | `/auth/login` | Autentica e retorna token JWT | ✅ |

---

### 👤 Clientes (`/clientes`)
| Método | Endpoint | Descrição | Autenticado |
|--------|-----------|------------|--------------|
| POST | `/clientes` | Cadastra novo cliente | 🔒 |
| GET | `/clientes` | Lista todos os clientes | 🔒 |
| GET | `/clientes/{id}` | Busca cliente por ID | 🔒 |
| PUT | `/clientes/{id}` | Atualiza cliente | 🔒 |
| DELETE | `/clientes/{id}` | Soft delete (inativa cliente) | 🔒 |

---

### 💬 Recomendações (`/recomendacoes`)
| Método | Endpoint | Descrição | Autenticado |
|--------|-----------|------------|--------------|
| GET | `/recomendacoes/cliente/{id}` | Gera recomendação com base no perfil | 🔒 |

---

## 🧪 Testes Automatizados

Foram implementados testes de integração e unidade para os principais componentes:

| Arquivo | Tipo de Teste | Descrição |
|----------|----------------|-----------|
| `AuthControllerIntegrationTest` | Integração | Testa fluxo de login e registro |
| `RecomendacaoServiceTest` | Unidade | Valida lógica de recomendação |
| `Sprint4SoaXpApplicationTests` | Smoke Test | Verifica inicialização da aplicação |

Para executar:
```bash
mvn clean test
```

---

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

- Java JDK 17+
- Maven 3+
- MySQL rodando localmente (porta 3306)

### ⚙️ Configuração do Banco de Dados

Crie o banco:
```sql
CREATE DATABASE xp_smart_advisor;
```

Atualize o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/xp_smart_advisor
spring.datasource.username=root
spring.datasource.password=SENHA_AQUI
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### ▶️ Rodando a aplicação

Pelo terminal:
```bash
mvn spring-boot:run
```

Ou via IntelliJ:
- Clique em **Run → Sprint4SoaXpApplication**

Servidor:  
👉 http://localhost:8080

---

## 🧩 Testando com Postman

1. **Registrar usuário**
   ```json
   POST /auth/register
   {
     "username": "roberto",
     "password": "123456",
     "role": "USER"
   }
   ```

2. **Login**
   ```json
   POST /auth/login
   {
     "username": "roberto",
     "password": "123456"
   }
   ```
   → Copie o valor do campo `token`.

3. **Acessar endpoints protegidos**
   - Adicione no Header:
     ```
     Authorization: Bearer <token_copiado>
     ```

4. **Criar Cliente**
   ```json
   POST /clientes
   {
     "nome": "João da Silva",
     "cpf": "12345678901",
     "perfilInvestidor": "MODERADO",
     "objetivoInvestimento": "LONGO_PRAZO"
   }
   ```

---

## 📘 Documentação da API

Após subir o projeto, acesse:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🛡️ Requisitos de Segurança (DevSecOps)

- Autenticação JWT e controle de acesso por roles.
- Criptografia de senhas com **BCrypt**.
- Proteção contra CSRF, CORS e XSS.
- Validação de entrada via DTOs e tratamentos globais de erro.
- Testes automatizados de autenticação e falhas.
- Configuração compatível com OWASP ZAP para análise de vulnerabilidades.

---

## 👥 Autores

**Roberto** — Desenvolvimento e integração  
**Assistência técnica** — GPT-5 (OpenAI)

---

## 📄 Licença

Este projeto é de uso acadêmico e não possui licença comercial.  
Sinta-se livre para estudar, modificar e adaptar o código.
