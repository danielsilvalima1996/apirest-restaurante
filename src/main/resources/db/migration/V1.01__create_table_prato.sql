CREATE TABLE IF NOT EXISTS prato(
	id BIGINT AUTO_INCREMENT NOT NULL,
	id_restaurante BIGINT NOT NULL,
	prato VARCHAR(255) NOT NULL,
	preco decimal(20,2) not null,
	CONSTRAINT PK_prato_id PRIMARY KEY (id),
	CONSTRAINT FK_prato_id_restaurante FOREIGN KEY (id_restaurante) REFERENCES restaurante(id)
);
