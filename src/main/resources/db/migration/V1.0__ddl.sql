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
CREATE TABLE musicien
(
    id_msc SERIAL NOT NULL,
    prénom VARCHAR(35)NOT NULL,
    nom VARCHAR(35) NOT NULL,
    instrument VARCHAR(35) NOT NULL,
    ville VARCHAR(35) NOT NULL,
    niveau INTEGER NOT NULL, /* 0: novice /1: expérimenté /2: professionnel */
    
    PRIMARY KEY (id)
);

CREATE TABLE Annonce
(
    id_anc SERIAL NOT NULL,
    auteur SERIAL NOT NULL,
    catégorie VARCHAR(35) NOT NULL,
    date DATE NOT NULL,
    heure TIME NOT NULL,
    groupe SERIAL NOT NULL,

    PRIMARY KEY (id)
);


CREATE TABLE groupe (
id_grp SERIAL NOT NULL,
nom_grp VARCHAR (35),
PRIMARY KEY (id_grp)
);

CREATE TABLE jouer (
id_musicien SERIAL NOT NULL,
id_groupe SERIAL NOT NULL,

PRIMARY KEY(id_musicien,id_groupe)
);

ALTER TABLE "jouer"
ADD CONSTRAINT cle_etrangere_jouer_vers_musicien FOREIGN KEY (id_musicien) REFERENCES "musicien" (id_msc) ;
ALTER TABLE "jouer"
ADD CONSTRAINT cle_etrangere_jouer_vers_groupe FOREIGN KEY (id_groupe) REFERENCES "groupe" (id_grp) ;
ALTER TABLE "annonce"
ADD CONSTRAINT cle_etrangere_annonce_vers_musicien FOREIGN KEY (auteur) REFERENCES "musicien" (id_msc) ;
ALTER TABLE "annonce"
ADD CONSTRAINT cle_etrangere_annonce_vers_groupe FOREIGN KEY (groupe) REFERENCES "groupe" (id_grp) ;
