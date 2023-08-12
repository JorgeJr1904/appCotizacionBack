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
    vPalabraClave varchar(40)
);

create table permisos(
    idPermiso serial primary key,
    vNombrePermiso varchar(20)
);

create table roles_permisos(
    idRolPermiso serial primary key,
    idRol int not null,
    idPermiso int not null,
    constraint roles_grupo foreign key (idRol) references roles (idRol),
    constraint permiso_grupo foreign key (idPermiso) references permisos (idPermiso)
);

create table usuario(
    idUsuario serial primary key,
    vNombreUsuario varchar(20) not null,
    vNombre varchar(30) not null,
    vApellido varchar(30) not null,
    vPassword varchar(100) not null,
    idRol int not null,
    constraint usuario_roles foreign key (idRol) references roles (idRol)
);

create table cotizaciones(
    idCotizacion serial primary key,
    idCliente int not null,
    idUsuario int not null,
    vNombreCliente varchar(30) not null,
    vApellidoCliente varchar(30) not null,
    dFechaCotizacion timestamp default current_timestamp,
    constraint usuario_cotizacion foreign key (idUsuario) references usuario (idUsuario)
);

create table pedido(
    idPedido serial primary key,
    vDescripcion varchar(100) not null,
    iCantidadHombres int not null,
    dPrecioHora decimal(10, 2) not null,
    iHorasDia int not null,
    iDias int not null,
    dPrecioTotal decimal(10, 2) not null,
    idCotizacion int not null,
    constraint pedido_cotizacion foreign key (idCotizacion) references cotizaciones (idCotizacion)
);

