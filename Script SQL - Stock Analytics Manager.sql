create schema db_teste;

use db_teste;

CREATE TABLE tb_produtos (
idProdutos INT NOT NULL AUTO_INCREMENT,

produto VARCHAR(32) NULL,
valor DECIMAL(10,2) NULL,
custo DECIMAL(10,2) NULL,
lucro DECIMAL(10,2) NULL,
vendamedia INT NULL,
estoque INT NULL,
dataRepor date,

PRIMARY KEY (idProdutos));

select * from tb_produtos;
