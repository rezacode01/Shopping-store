CREATE TABLE product_base
(
    product_id  BIGSERIAL,
    title       CHARACTER VARYING (255) NOT NULL ,
    barcode     CHARACTER VARYING (255) NOT NULL ,
    price       BIGINT  NOT NULL ,

    PRIMARY KEY (product_id)
)