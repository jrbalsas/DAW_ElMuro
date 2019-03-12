
/** Derby Schema and sample data 
 * 
 */
drop table Mensajes;

CREATE TABLE Mensajes (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
identificador VARCHAR(25) NOT NULL,
mensaje VARCHAR(100)
);

insert into  Mensajes (identificador,mensaje) values ('Pepe','Hola, como estamos?');
insert into  Mensajes (identificador,mensaje) values ('Carlos','No estamos mal');
insert into  Mensajes (identificador,mensaje) values ('Marta','A ver si quedamos');

/**JDBC Realm tables and sample data*/

create table Usuarios (
 usuario VARCHAR(50) not null primary key,
  clave VARCHAR(50) not null );

create table Roles (
   usuario VARCHAR(50) not null,
   rol VARCHAR(50) not null );

insert into Usuarios values ('pepe','secreto');
insert into Usuarios values ('maria','123456');
insert into Roles values ('pepe','USUARIOS');
insert into Roles values ('maria','ADMINISTRADORES');
