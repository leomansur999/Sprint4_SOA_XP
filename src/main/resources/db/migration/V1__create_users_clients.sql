CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS clientes (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        ativo BOOLEAN DEFAULT TRUE,
                                        nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    nacionalidade VARCHAR(50),
    data_nascimento DATE,
    telefone VARCHAR(30),
    email VARCHAR(150),
    renda_mensal DOUBLE,
    patrimonio_liquido DOUBLE,
    profissao VARCHAR(150),
    experiencia_investimentos VARCHAR(255),
    perfil_investidor VARCHAR(50),
    objetivo_investimento VARCHAR(50),
    banco VARCHAR(100),
    agencia VARCHAR(50),
    numero_conta VARCHAR(50),
    tipo_conta VARCHAR(50),
    titular_conta VARCHAR(150),
    logradouro VARCHAR(255),
    numero VARCHAR(50),
    complemento VARCHAR(255),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(10),
    cep VARCHAR(20)
    );
