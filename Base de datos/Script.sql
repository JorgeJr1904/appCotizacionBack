use app_cotizacion;
SET FOREIGN_KEY_CHECKS = 0;
drop table if exists departamento;
drop table if exists pedido;
drop table if exists roles;
drop table if exists permisos;
drop table if exists datos_persona;
drop table if exists roles_permisos;
drop table if exists cotizaciones;

create table departamento(
idDepartamento int primary key not null auto_increment,
vNombreDep varchar(30) not null,
vNombreColaborador varchar(30) not null,
vApellidoColaborador varchar(30) not null,
vPuestoColaborador varchar(30) not null
);

create table pedido(
idPedido int primary key not null auto_increment,
vDescripcion varchar(60) not null,
iCantidadHombres int not null,
dPrecioHora decimal(10, 2) not null,
iHorasDia int not null,
iDias int not null,
iPrecioTotal decimal(10, 2) not null
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

create table datos_persona(
idDatos_persona int primary key not null auto_increment,
vNombreUsuario varchar(20) not null,
vNombre varchar(30) not null,
vApellido varchar(30) not null,
vPassword varchar(100) not null,
idRol int not null,
constraint datos_persona_roles foreign key (idRol) references roles (idRol)
);


create table cotizaciones(
idCotizacion int primary key not null auto_increment,
idDepartamento int not null,
idPedido int not null,
idDatosPersona int not null,
dFechaCotizacion date default (date(NOW())),
constraint departamento_cotizacion foreign key (idDepartamento) references departamento (idDepartamento),
constraint pedido_cotizacion foreign key (idPedido) references pedido (idPedido),
constraint persona_cotizacion foreign key (idDatosPersona) references datos_persona (idDatos_Persona)
);

SET FOREIGN_KEY_CHECKS = 1;



