DROP TABLE IF EXISTS TEST10 ;
DROP TABLE IF EXISTS TEST11 ;

create table TEST10 (
    ID SERIAL PRIMARY KEY,
    label10 VARCHAR(100) NOT NULL
);

create table TEST11 (
    ID int not null,
    label11 VARCHAR(100)
);
