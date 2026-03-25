-- habilidades
INSERT INTO habilidade (nome) VALUES ('Primeiros Socorros');
INSERT INTO habilidade (nome) VALUES ('Educação');
INSERT INTO habilidade (nome) VALUES ('Transporte');
INSERT INTO habilidade (nome) VALUES ('Cozinha');
INSERT INTO habilidade (nome) VALUES ('Saúde');
INSERT INTO habilidade (nome) VALUES ('Construção');

-- usuarios voluntarios
INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, cep, tipo)
VALUES ('Maria Silva', 'maria', '123', 'maria@email.com', '(54)99999-1111', 'Rua das Flores', '123', 'Centro', 'Passo Fundo', 'RS', '99010-060', 'VOL');

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, cep, tipo)
VALUES ('João Santos', 'joao', '123', 'joao@email.com', '(54)99999-2222', 'Rua das Acácias', '456', 'Centro', 'Passo Fundo', 'RS', '99010-060', 'VOL');

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, cep, tipo)
VALUES ('Ana Oliveira', 'ana', '123', 'ana@email.com', '(54)99999-3333', 'Rua dos Pinheiros', '789', 'São José', 'Passo Fundo', 'RS', '99035-180', 'VOL');

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, cep, tipo)
VALUES ('Carlos Souza', 'carlos', '123', 'carlos@email.com', '(54)99999-4444', 'Rua Bento Gonçalves', '321', 'Nossa Senhora', 'Passo Fundo', 'RS', '99070-010', 'VOL');

INSERT INTO usuario (nome, login, senha, email, telefone, rua, numero, bairro, cidade, estado, cep, tipo)
VALUES ('Paula Lima', 'paula', '123', 'paula@email.com', '(54)99999-5555', 'Rua Morom', '654', 'Centro', 'Erechim', 'RS', '99700-010', 'VOL');

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
