CREATE TABLE tb_categoria (
    id CHAR(36) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE tb_post
ADD COLUMN categoria_id CHAR(36);

ALTER TABLE tb_post
ADD CONSTRAINT fk_post_categoria
FOREIGN KEY (categoria_id)
REFERENCES tb_categoria(id);