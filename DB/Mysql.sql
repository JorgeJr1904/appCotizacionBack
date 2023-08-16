use app_cotizacion;
SET FOREIGN_KEY_CHECKS = 0;
drop table if exists pedido;
drop table if exists roles;
drop table if exists permisos;
drop table if exists usuario;
drop table if exists roles_permisos;
drop table if exists cotizaciones;

-- Creación de las tablas
create table roles(
    idRol int auto_increment primary key not null,
    vNombreRol varchar(20),
    vPalabraClave varchar(40),
    cEstado char not null
);

create table permisos(
    idPermiso int auto_increment primary key not null,
    vNombrePermiso varchar(20),
    cEstado char not null
);

create table roles_permisos(
    idRolPermiso int auto_increment primary key not null,
    idRol int not null,
    idPermiso int not null,
    cEstado char not null,
    foreign key (idRol) references roles (idRol),
    foreign key (idPermiso) references permisos (idPermiso)
);

create table usuario(
    idUsuario int auto_increment primary key not null,
    vNombreUsuario varchar(20) not null,
    vNombre varchar(30) not null,
    vApellido varchar(30) not null,
    vPassword varchar(100) not null,
    idRol int not null,
    cEstado char not null,
    foreign key (idRol) references roles (idRol)
);

create table cotizaciones(
    idCotizacion int auto_increment primary key not null,
    idUsuario int not null,
    vNombreCliente varchar(30) not null,
    vApellidoCliente varchar(30) not null,
    vTipoCliente enum ("Interno", "Externo") not null,
    dFechaCotizacion timestamp default current_timestamp,
    dPrecioTotal decimal(10,2) not null,
    cEstado char not null,
    foreign key (idUsuario) references usuario (idUsuario)
);

create table pedido(
    idPedido int auto_increment primary key not null,
    vDescripcion varchar(100) not null,
    iCantidadHombres int not null,
    dPrecioHora decimal(10, 2) not null,
    iHorasDia int not null,
    iDias int not null,
    dPrecioPedido decimal(10, 2) not null,
    idCotizacion int not null,
    cEstado char not null,
    foreign key (idCotizacion) references cotizaciones (idCotizacion)
);


SET FOREIGN_KEY_CHECKS = 1;

