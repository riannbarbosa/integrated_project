CREATE DATABASE salesystem;

\c salesystem


CREATE TABLE IF NOT EXISTS public.usuario (
    id_usuario SERIAL PRIMARY KEY,
    user TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL ,
    password TEXT NOT NULL   
);
--

CREATE TABLE IF NOT EXISTS resources (
    res_id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    datebuy DATE NOT NULL,
    value NUMERIC(10,2) NOT NULL,
    desc_type VARCHAR(255)
);
--
CREATE TABLE IF NOT EXISTS client (
    id_client SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    city  VARCHAR(25) NOT NULL,
    street VARCHAR(25),
    neighborhood VARCHAR(10),
    number VARCHAR(10)

);
    



CREATE TABLE IF NOT EXISTS orders (
    order_id SERIAL PRIMARY KEY,
    client_id INT NOT NULL,
    name VARCHAR(30) NOT NULL,
    order_date DATE NOT NULL,
    status BOOLEAN NOT NULL,
    value NUMERIC(10,2) NOT NULL,
    description VARCHAR(255),
    
    CONSTRAINT fk_orders_client FOREIGN KEY (client_id) REFERENCES client(id_client));