use app_cotizacion;
SET FOREIGN_KEY_CHECKS = 0;
drop table if exists departamento;
drop table if exists pedido;
drop table if exists roles;
drop table if exists permisos;
drop table if exists datos_persona;
drop table if exists roles_permisos;
drop table if exists cotizaciones;

create table cliente(
idCliente int primary key not null auto_increment,
tipo enum('Interno', 'Externo') not null,
vNombreCliente varchar(30) not null,
vApellidoCliente varchar(30) not null,
vDescripcion varchar(100) not null
);

create table roles(
idRol int primary key not null auto_increment,
vNombreRol varchar(20),
vPalabraClave varchar(40)
);

create table permisos(
idPermiso int primary key not null auto_increment,
vNombrePermiso varchar(20)
);

create table roles_permisos(
idRolPermiso int primary key not null auto_increment,
idRol int not null,
idPermiso int not null,
constraint roles_grupo foreign key (idRol) references roles (idRol),
constraint permiso_grupo foreign key (idPermiso) references permisos (idPermiso)
);

create table usuario(
idUsuario int primary key not null auto_increment,
vNombreUsuario varchar(20) not null,
vNombre varchar(30) not null,
vApellido varchar(30) not null,
vPassword varchar(100) not null,
idRol int not null,
constraint usuario_roles foreign key (idRol) references roles (idRol)
);


create table cotizaciones(
idCotizacion int primary key not null auto_increment,
idCliente int not null,
idUsuario int not null,
dFechaCotizacion datetime default CURRENT_TIMESTAMP,
constraint cliente_cotizacion foreign key (idCliente) references cliente (idCliente),
constraint usuario_cotizacion foreign key (idUsuario) references usuario (idUsuario)
);

create table pedido(
idPedido int primary key not null auto_increment,
vDescripcion varchar(100) not null,
iCantidadHombres int not null,
dPrecioHora decimal(10, 2) not null,
iHorasDia int not null,
iDias int not null,
iPrecioTotal decimal(10, 2) not null,
idCotizacion int not null,
constraint pedido_cotizacion foreign key (idCotizacion) references cotizaciones (idCotizacion)
);

SET FOREIGN_KEY_CHECKS = 1;



