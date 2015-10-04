CREATE DATABASE BibliotecaEjercicio
GO
USE BibliotecaEjercicio 
GO
CREATE TABLE Usuario(
idUsuario INT IDENTITY (1,1) PRIMARY KEY,
nombre VARCHAR(10) NOT NULL,
telefono INT NOT NULL,
direccion VARCHAR (20) NOT NULL
)
GO
CREATE TABLE Autor(
idAutor INT IDENTITY (1,1) PRIMARY KEY,
nombre VARCHAR (20) NOT NULL,
)
GO
CREATE TABLE Libro(
idLibro INT IDENTITY (1,1) PRIMARY KEY,
titulo VARCHAR (20) NOT NULL,
isbn VARCHAR (20) UNIQUE NOT NULL,
editorial VARCHAR (10) NOT NULL,
paginas INT NOT NULL,
)
GO
CREATE TABLE LibroEscrito(
idAutor INT FOREIGN KEY REFERENCES Autor (idAutor),
idLibro INT FOREIGN KEY REFERENCES Libro(idLibro),
) 
GO
CREATE TABLE Ejemplar(
idEjemplar INT IDENTITY (1,1) PRIMARY KEY,
localizacion VARCHAR (20) NOT NULL,
idLibro INT FOREIGN KEY REFERENCES Libro(idLibro),
)
GO
CREATE TABLE prestamo(
idPrestamo INT IDENTITY (1,1) PRIMARY KEY,
idUsuario INT FOREIGN KEY REFERENCES Usuario(idUsuario),
idEjemplar INT FOREIGN KEY REFERENCES Ejemplar(idEjemplar),
fechaPrestamo DATE NOT NULL,
fechaDevolucion DATE,
)


INSERT INTO Usuario VALUES ('Eddie Alvarez', '45210100', 'Zona 3')
INSERT INTO Usuario VALUES ('Pablo Nij', '52195588', 'Zona 7')
INSERT INTO Usuario VALUES ('Axel Ramos', '45288855', 'San Miguel Petapa')
INSERT INTO Usuario VALUES ('Steven Ramos', '88552221', 'Nebaj, Quiche')
INSERT INTO Usuario VALUES ('Pablo Zarat', '46108100', 'Palin, Escuintla')
INSERT INTO Usuario VALUES ('Pablo Yaxon', '48584121', 'Tac Tic, Coban')
INSERT INTO Usuario VALUES ('Eddie Flores', '56251232', 'Zona 10')
INSERT INTO Usuario VALUES ('Antonio Escobar', '45285522', 'Zona 9')
INSERT INTO Usuario VALUES ('Andrea Escobar', '66521478', 'Zona 14')
INSERT INTO Usuario VALUES ('Maria Fernanda Guidel', '45218522', 'Zona 10')

INSERT INTO Autor VALUES 
