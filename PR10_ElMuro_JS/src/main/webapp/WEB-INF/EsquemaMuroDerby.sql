
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
