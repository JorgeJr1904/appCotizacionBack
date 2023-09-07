-- SET FOREIGN_KEY_CHECKS = 0;
drop table if exists pedido;
drop table if exists cotizaciones;
drop table if exists usuario;
drop table if exists roles_permisos;
drop table if exists roles;
drop table if exists permisos;




-- Creación de las tablas
create table roles(
    idRol serial primary key,
    vNombreRol varchar(20),
    cEstado char not null
);

create table permisos(
    idPermiso serial primary key,
    vNombrePermiso varchar(40),
    cEstado char not null
);

create table roles_permisos(
    idRolPermiso serial primary key,
    idRol int not null,
    idPermiso int not null,
    cEstado char not null,
    constraint roles_grupo foreign key (idRol) references roles (idRol) on delete CASCADE,
    constraint permiso_grupo foreign key (idPermiso) references permisos (idPermiso) on delete CASCADE
);

create table usuario(
    idUsuario serial primary key,
    vNombreUsuario varchar(20) not null,
    vNombre varchar(60) not null,
    vApellido varchar(60) not null,
    vPassword varchar(100) not null,
    idRol int not null,
    cEstado char not null,
    constraint usuario_roles foreign key (idRol) references roles (idRol) on delete set null
);

create table cotizaciones(
    idCotizacion serial primary key,
    idUsuario int not null,
    vNombreCliente varchar(30) not null,
    vApellidoCliente varchar(30) not null,
    vTipoCliente varchar(30) not null,
    dFechaCotizacion timestamp default current_timestamp not null,
    dPrecioTotal decimal(10,2) not null,
    cEstado char not null,
    constraint usuario_cotizacion foreign key (idUsuario) references usuario (idUsuario)
);

create table pedido(
    idPedido serial primary key,
    vDescripcion varchar(100) not null,
    iCantidadHombres int not null,
    dPrecioHora decimal(10, 2) not null,
    iHorasDia int not null,
    iDias int not null,
    dPrecioPedido decimal(10, 2) not null,
    idCotizacion int not null,
    cEstado char not null,
    constraint pedido_cotizacion foreign key (idCotizacion) references cotizaciones (idCotizacion) on delete CASCADE
    
);

--Functions

CREATE OR REPLACE FUNCTION findUser(a varchar(20))
RETURNS integer AS
$$
BEGIN
    select count(*) from usuario where vNombreUsuario = a;
END;
$$ LANGUAGE plpgsql;


SHOW TIME ZONE;


