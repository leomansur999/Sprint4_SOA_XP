CREATE TABLE IF NOT EXISTS cliente_preferencia_investimentos (
                                                                 cliente_id BIGINT NOT NULL,
                                                                 preferencia VARCHAR(150) NOT NULL,
                                                                 PRIMARY KEY (cliente_id, preferencia),
                                                                 CONSTRAINT fk_cliente_pref FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);
