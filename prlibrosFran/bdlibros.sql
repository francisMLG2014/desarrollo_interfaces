use bdlibros;
create table libros(
	id integer auto_increment primary key,
    titulo varchar(80)not null,
    editorial varchar(80)not null,
    autor varchar (80)not null,
    paginas integer not null
    );


select * from libros;