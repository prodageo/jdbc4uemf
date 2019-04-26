DROP TABLE IF EXISTS TEST10 ;
DROP TABLE IF EXISTS TEST11 ;

-- info : SERIAL is an INTEGER (from : https://www.postgresql.org/docs/9.1/datatype-numeric.html)
create table TEST10 (
    id SERIAL PRIMARY KEY,
    label10 VARCHAR(100) NOT NULL
);

create table TEST11 (
    id int not null,
    label11 VARCHAR(100)
);
