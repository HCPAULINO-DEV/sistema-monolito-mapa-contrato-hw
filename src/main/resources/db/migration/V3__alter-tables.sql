-- Adiciona a coluna
ALTER TABLE equipamento
ADD COLUMN contrato_id UUID;

-- Cria a foreign key
ALTER TABLE equipamento
ADD CONSTRAINT fk_equipamento_contrato
FOREIGN KEY (contrato_id)
REFERENCES contrato(id)
ON DELETE CASCADE;

-- (Opcional, mas recomendado) índice para performance
CREATE INDEX idx_equipamento_contrato_id
ON equipamento(contrato_id);