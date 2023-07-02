drop database if exists Bicicletarias;

create database Bicicletarias;

use Bicicletarias;

-- Tabela Usuario
CREATE TABLE Usuario (
  id bigint not null auto_increment PRIMARY KEY,
  email VARCHAR(100),
  senha VARCHAR(255) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  papel VARCHAR(15) NOT NULL
);

-- Tabela de clientes
CREATE TABLE Cliente (
  id_usuario bigint not null,
  CPF VARCHAR(11) PRIMARY KEY,
  telefone VARCHAR(20) NOT NULL,
  sexo ENUM('Masculino', 'Feminino', 'Outro') NOT NULL,
  data_nascimento DATE NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES Usuario (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabela de locadoras
CREATE TABLE Locadora (
  id_usuario bigint not null,
  CNPJ VARCHAR(14) PRIMARY KEY,
  cidade VARCHAR(100) NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES Usuario (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabela de locações
CREATE TABLE Locacao (
  cliente_CPF VARCHAR(11) NOT NULL,
  locadora_CNPJ VARCHAR(14) NOT NULL,
  data_hora DATETIME NOT NULL,
  PRIMARY KEY (cliente_CPF, locadora_CNPJ, data_hora),
  FOREIGN KEY (cliente_CPF) REFERENCES Cliente (CPF) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (locadora_CNPJ) REFERENCES Locadora (CNPJ) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT unique_locacao_cliente_datahora UNIQUE (cliente_CPF, data_hora),
  CONSTRAINT unique_locacao_locadora_datahora UNIQUE (locadora_CNPJ, data_hora)
);

-- Inserindo dados

-- Inserções na tabela Usuario
INSERT INTO Usuario (email, senha, nome, papel) VALUES ('usuario1@gmail.com', 'usuario1', 'Usuario 1', 'Cliente');
INSERT INTO Usuario (email, senha, nome, papel) VALUES ('usuario2@gmail.com', 'usuario2', 'Usuario 2', 'Cliente');
INSERT INTO Usuario (email, senha, nome, papel) VALUES ('usuario3@gmail.com', 'usuario3', 'Usuario 3', 'Locadora');
INSERT INTO Usuario (email, senha, nome, papel) VALUES ('usuario4@gmail.com', 'usuario4', 'Usuario 4', 'Locadora');
INSERT INTO Usuario (email, senha, nome, papel) VALUES ('usuario5@gmail.com', 'usuario5', 'Usuario 5', 'ADMIN');

-- Inserções na tabela Cliente
INSERT INTO Cliente (id_usuario, CPF, telefone, sexo, data_nascimento) VALUES (1, '00000000001', '111111111', 'Masculino', '1990-01-01');
INSERT INTO Cliente (id_usuario, CPF, telefone, sexo, data_nascimento) VALUES (2, '00000000002', '222222222', 'Feminino', '1985-05-10');

-- Inserções na tabela Locadora
INSERT INTO Locadora (id_usuario, CNPJ, cidade) VALUES (3, '00000000000001', 'São Carlos');
INSERT INTO Locadora (id_usuario, CNPJ, cidade) VALUES (4, '00000000000002', 'São Paulo');


-- Inserções na tabela Locacao
INSERT INTO Locacao (cliente_CPF, locadora_CNPJ, data_hora) VALUES ('00000000001', '00000000000001', '2023-06-28 10:00:00');
INSERT INTO Locacao (cliente_CPF, locadora_CNPJ, data_hora) VALUES ('00000000002', '00000000000002', '2023-06-29 14:00:00');


