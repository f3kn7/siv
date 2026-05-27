-- habilidades
INSERT INTO habilidade (nome) VALUES ('Primeiros Socorros');
INSERT INTO habilidade (nome) VALUES ('Educação');
INSERT INTO habilidade (nome) VALUES ('Transporte');
INSERT INTO habilidade (nome) VALUES ('Cozinha');
INSERT INTO habilidade (nome) VALUES ('Saúde');
INSERT INTO habilidade (nome) VALUES ('Construção');

-- usuarios voluntarios
INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, tipo)
VALUES ('Maria Silva', 'maria', '123', 'maria@email.com', '(54)99999-1111', 'Rua das Flores', '123', 'Centro', 'Passo Fundo', 'RS',  'VOL');

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, tipo)
VALUES ('João Santos', 'joao', '123', 'joao@email.com', '(54)99999-2222', 'Rua das Acácias', '456', 'Centro', 'Passo Fundo', 'RS',  'VOL');

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, tipo)
VALUES ('Ana Oliveira', 'ana', '123', 'ana@email.com', '(54)99999-3333', 'Rua dos Pinheiros', '789', 'São José', 'Passo Fundo', 'RS',  'VOL');

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, tipo)
VALUES ('Carlos Souza', 'carlos', '123', 'carlos@email.com', '(54)99999-4444', 'Rua Bento Gonçalves', '321', 'Nossa Senhora', 'Passo Fundo', 'RS', 'VOL');

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, tipo)
VALUES ('Paula Lima', 'paula', '123', 'paula@email.com', '(54)99999-5555', 'Rua Morom', '654', 'Centro', 'Erechim', 'RS',  'VOL');

-- tabela filha voluntario
INSERT INTO voluntario (fk_usuario, cpf) VALUES (1, '111.111.111-11');
INSERT INTO voluntario (fk_usuario, cpf) VALUES (2, '222.222.222-22');
INSERT INTO voluntario (fk_usuario, cpf) VALUES (3, '333.333.333-33');
INSERT INTO voluntario (fk_usuario, cpf) VALUES (4, '444.444.444-44');
INSERT INTO voluntario (fk_usuario, cpf) VALUES (5, '555.555.555-55');

-- disponibilidades variadas para testar match
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('SABADO', '08:00:00', '18:00:00', 1);
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('DOMINGO', '08:00:00', '12:00:00', 1);
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('SABADO', '13:00:00', '18:00:00', 2);
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('QUARTA', '08:00:00', '17:00:00', 2);
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('SABADO', '08:00:00', '12:00:00', 3);
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('DOMINGO', '08:00:00', '18:00:00', 3);
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('SEGUNDA', '08:00:00', '17:00:00', 4);
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('SABADO', '08:00:00', '18:00:00', 4);
INSERT INTO disponibilidade (dia_semana, hora_inicio, hora_fim, fk_voluntario) VALUES ('SABADO', '08:00:00', '18:00:00', 5);

-- habilidades dos voluntarios
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (1, 2);
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (1, 1);
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (2, 5);
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (2, 2);
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (3, 2);
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (3, 4);
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (4, 6);
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (4, 3);
INSERT INTO voluntario_habilidade (fk_voluntario, fk_habilidade) VALUES (5, 2);

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, tipo)
VALUES ('Hospital Passo Fundo', 'hpf', '123', 'hpf@email.com', '(54)3300-0000',
        'Rua Teixeira Soares', '600', 'Centro', 'Passo Fundo', 'RS', 'INST');

-- fk_usuario = 6
INSERT INTO instituicao (fk_usuario, cnpj, estatuto_social)
VALUES (6, '11111111000111', 'Instituição de saúde sem fins lucrativos.');

-- ============================================================
-- TAREFA
-- SABADO, Passo Fundo, das 09h às 12h
-- propositalmente compatível com voluntários que já existem no banco:
--  voluntário 1: SABADO 08-18h, habilidade Educação, Passo Fundo
--  voluntário 3: SABADO 08-12h, habilidade Educação, Passo Fundo
-- ============================================================

INSERT INTO tarefa (nome, descricao, data, dia_semana, hora_inicio, hora_fim,
                    rua, bairro, cidade, estado, status, fk_instituicao)
VALUES ('Aula de reforço', 'Reforço escolar para crianças', '2026-11-07', 'SABADO',
        '09:00:00', '12:00:00', 'Rua Central', 'Centro', 'Passo Fundo', 'RS', 'ABERTA', 6);

-- ============================================================
-- REQUISIÇÃO DE HABILIDADE
-- a tarefa precisa de 1 voluntário com habilidade Educação (id = 2)
-- com FATOR_LIMITE = 2, o sistema vai buscar 1 × 2 = 2 candidatos
-- ============================================================

INSERT INTO requisicao_habilidade (qtd_habilidade, fk_habilidade, fk_tarefa)
VALUES (1, 2, 1);
