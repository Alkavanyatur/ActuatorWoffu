CREATE DATABASE FICHAJE;


USE FICHAJE;

CREATE TABLE EMPLEADOS (
	ID_EMPLEADO int AUTO_INCREMENT NOT NULL,
    ID_empleadosEMPLEADO_WOFFU INT NOT NULL,
	NOMBRE VARCHAR(50) NOT NULL,
    APELLIDOS VARCHAR(50) NOT NULL,
    TOKEN VARCHAR(500) NULL,
    BEARER VARCHAR (1200) NULL,
    BORRADO BOOLEAN DEFAULT 0,
	PRIMARY KEY (ID_EMPLEADO)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ACCIONES (
	ID_ACCION  int AUTO_INCREMENT  NOT NULL,
    ACCION VARCHAR(50),
    PRIMARY KEY (ID_ACCION)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE AUDITORIA_EMPLEADOS(
	ID_AUDITORIA  int AUTO_INCREMENT  NOT NULL,
    ID_EMPLEADO INT NOT NULL,
    FECHA DATE NOT NULL,
    ID_ACCION INT NOT NULL,
    PRIMARY KEY (ID_AUDITORIA),
	FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADOS(ID_EMPLEADO),
    FOREIGN KEY (ID_ACCION) REFERENCES ACCIONES(ID_ACCION)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE AUDITORIA_EMPLEADOS;
DROP TABLE ACCIONES;
DROP TABLE EMPLEADOS;