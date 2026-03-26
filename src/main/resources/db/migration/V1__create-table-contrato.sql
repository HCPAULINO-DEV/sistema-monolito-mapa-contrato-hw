    CREATE TABLE contrato (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

        cod_erp VARCHAR(255),
        numero_contrato_erp VARCHAR(255),

        empresa VARCHAR(255),
        cidade VARCHAR(255),
        estado VARCHAR(255),

        situacao VARCHAR(50),

        base_atendimento VARCHAR(255),
        equipe_atendimento VARCHAR(255),

        equipamentos_atendidos TEXT,
        particularidade_contrato TEXT,
        sla_atendimento_contratado TEXT,

        preventiva VARCHAR(50),

        corretiva TEXT,
        laboratorio TEXT,
        despesa_viagem_frete TEXT,
        pecas TEXT,

        contato VARCHAR(255),
        telefone VARCHAR(50),
        email VARCHAR(255),

        endereco TEXT,

        executivo_responsavel_demandas_comerciais VARCHAR(255)
    );