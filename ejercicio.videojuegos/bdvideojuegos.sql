use bdvideojuegos;
create table juegos(
	id integer auto_increment primary key,
    nombre varchar(100) unique not null,
    precio numeric (6,2) not null,
    consola varchar(100) not null,
    PEGI integer not null
    
    );
    
    select * from juegos;