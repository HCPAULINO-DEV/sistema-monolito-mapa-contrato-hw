ALTER TABLE contrato
ADD COLUMN cnpj VARCHAR(20);

-- opcional: índice para busca
CREATE INDEX idx_contrato_cnpj ON contrato(cnpj);