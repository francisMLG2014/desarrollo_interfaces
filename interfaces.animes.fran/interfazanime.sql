use interfazanime;
CREATE TABLE animes(
	id integer primary key auto_increment,
    nombre varchar(100) unique not null,
    estudio varchar(100) not null,
    temporadas integer not null,
    en_emision boolean not null
    );
    
    select * from animes;
    