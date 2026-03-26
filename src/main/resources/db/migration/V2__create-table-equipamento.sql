CREATE TABLE equipamento (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo_interno VARCHAR(30) NOT NULL,
    tipo_equipamento VARCHAR(30) NOT NULL,
    fabricante VARCHAR(30),
    descricao VARCHAR(30),
    local VARCHAR(30),
    identificacao_rede VARCHAR(30)
);