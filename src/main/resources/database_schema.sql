CREATE TABLE cliente(
  cod_cliente BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100),
  cpf VARCHAR(11),
  data_nascimento DATE,
  email VARCHAR(70),
  endereco VARCHAR(100),
  cidade VARCHAR(70),
  telefone VARCHAR(30)
);