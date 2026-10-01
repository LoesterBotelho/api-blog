CREATE TABLE tb_post (
    id CHAR(36) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    data DATE NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    texto TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_comment (
    id CHAR(36) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    data DATE NOT NULL,
    texto TEXT NOT NULL,
    post_id CHAR(36) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_tb_comment_post
        FOREIGN KEY (post_id)
        REFERENCES tb_post (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE INDEX idx_tb_comment_post_id
    ON tb_comment (post_id);
