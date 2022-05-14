DROP DATABASE IF EXISTS el_rincon_del_tragon_db;
CREATE DATABASE el_rincon_del_tragon_db;
USE el_rincon_del_tragon_db;

-- Drop Tables
        
DROP TABLE IF EXISTS users; 
   


CREATE TABLE users (
                            
	email VARCHAR(100) NOT NULL PRIMARY KEY,
                                            
	password VARCHAR(100) NOT NULL,

	firstname VARCHAR(100) NOT NULL,

	lastname VARCHAR(100) NOT NULL
	
    -- days_of_password_validity INTEGER NOT NULL, -- En dias

	-- date_of_last_password_update TIMESTAMP NOT NULL,
	
	-- is_temporal_password BOOLEAN NOT NULL,
	
	-- activation_key VARCHAR(50),
	
	-- status VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS recipes;

CREATE TABLE recipes (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	recipe_creator_email VARCHAR(100) NOT NULL,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(250) NOT NULL,
	ingredient_list VARCHAR(2500) NOT NULL,
	recipe_procedure VARCHAR(2500) NOT NULL,
	FOREIGN KEY (recipe_creator_email) REFERENCES users(email)
);

INSERT INTO users VALUES ("admin@elrincondeltragon.net", "passw0rd", "Admin", "");
INSERT INTO users VALUES ("guillermart@gmail.com", "passw0rd", "Guillermo", "Martinez");
INSERT INTO users VALUES ("rebeca.strempler@gmail.com", "passw0rd", "Rebeca", "Strempler");
INSERT INTO users VALUES ("anton.corbijn@gmail.com", "passw0rd", "Anton", "Corbijn");

INSERT INTO recipes (recipe_creator_email, name, description, ingredient_list, recipe_procedure)
VALUES( 
"rebeca.strempler@gmail.com",
"Pizza Diavola", 
"Pizza con pepperoni y jitomate, y un ligero y delicioso sabor picante.", 
"500 gramos Harina de trigo; 4.5 gr de levadura instantanea; ...", 
"1.- Colocar 80 ml de agua tibia en un molde, agregar el azucar y la levadura, mover ligeramente por unos 30 segundos y dejar reposar 5 min.; 2.- Colocar la harina en un molde de plastico; 3.- En un " );


INSERT INTO recipes (recipe_creator_email, name, description, ingredient_list, recipe_procedure)
VALUES( 
"rebeca.strempler@gmail.com",
"Sopes Tricolor", 
"Deliciosos sopes con frijol, pollo, chorizo verde y chorizo rojo.", 
"500 gramos masa de harina de maiz; 300 gr de frijoles cocidos; ...", 
"1.- Colocar la masa en un molde de plastico;2.- Agregar 1 taza de agua y amasar;3.- En un " );

INSERT INTO recipes (recipe_creator_email, name, description, ingredient_list, recipe_procedure)
VALUES( 
"anton.corbijn@gmail.com",
"Hamburguesas Barbecue", 
"Hamburguesas de sirloin con tocino aderezadas con una deliciosa salsa barbecue New York", 
"750 gramos de sirloin molida; 300 gr de tocino en rebanadas; ...", 
"1.- Colocar la carneen un molde de plastico;2.- Agregar 1/4 de taza de aceite de oliva;3.- En un " );


ALTER TABLE recipes ADD image VARCHAR(15) AFTER recipe_procedure;

UPDATE recipes SET image='pizza.jpg' WHERE id =1;

UPDATE recipes SET image='sopes.jpg' WHERE id =2;

UPDATE recipes SET image='hamburguesa.jpg' WHERE id =3;

INSERT INTO users(email,password,firstname,lastname) VALUES ("edjai8991@hotmail.com", "contrasenia", "Jaime", "Ortiz");
INSERT INTO users(email,password,firstname,lastname) VALUES ("slander@gmail.com", "contrasenia", "Equipo", "Slander");
