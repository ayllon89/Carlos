SELECT * FROM usuarios u;
SELECT * FROM cuentas c;

SELECT * FROM periodo p;
SELECT * FROM empresas e;
SELECT * FROM indicadores i;
SELECT * FROM metodologias m;
SELECT * FROM condiciones c;

update periodo set valorCuenta=7579721 where idPeriodo=11;update periodo set valorCuenta=7579721 where idPeriodo=11;

INSERT INTO inversiones.metodologias
values(1,"La_menos_jugada","Ordenamiento",2);

UPDATE metodologias set nombreMetodologia="La_menos_jugada" where idMetodologia=3;

UPDATE condiciones SET 
idMetodologia = 1 where idCondicion =1
;
SELECT * FROM cuentas c WHERE idEmpresa = 1;

SELECT nombreEmpresa FROM Empresas e;
-- para poder eliminar todo :)
SET SQL_SAFE_UPDATES=0;

DROP TABLE periodo;
DROP TABLE condiciones;
DROP TABLE indicadores;
DROP TABLE metodologias;
DROP TABLE cuentas;
DROP TABLE usuarios;
DROP TABLE empresas;





















