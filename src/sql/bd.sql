
create database clientes;

use clientes;

create table usuarios(
    documento varchar(50) not null,
    Telefono varchar(50) not null,
    nombre varchar(100) not null,
    direccion varchar(100) not null,
    primary key(documento)
);

