CREATE TABLE IF NOT EXISTS assets (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      codigo VARCHAR(50) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    classe VARCHAR(100) NOT NULL,
    descricao TEXT,
    risco VARCHAR(50),
    liquidez VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS recomendacoes (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             cliente_id BIGINT NOT NULL,
                                             texto TEXT NOT NULL,
                                             data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                             CONSTRAINT fk_recomendacao_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
    );
