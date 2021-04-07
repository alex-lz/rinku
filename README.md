## Rinku
> **Nota:** crearemos el frontend en ReactJS.


### Descripción

* Gestor de nominas, desarrollado en el lenguaje **Java** con el framework **Spring Boot** y base de datos en **postgreSQL**.

### Comencemos!
1. Entra a la carpeta del proyecto y abre la carpeta *server* luego corre **Application.java** esto iniciara la aplicación el el puerto *port 8083* la url completa debe ser `http://localhost:8083`.

1. Get all employees path - Read
   - /employe/allemployes
2. Register employe path - Create
   - /register/employe
3. Delete employe path - Delete
   - /delete/employe/{*regdNum*}
4. Update employe path - Update
   - /update/employe

### Base de datos(postgreSQL) - employees
```
CREATE TABLE employees(
   numero serial PRIMARY KEY,
   nombre VARCHAR (50) NOT NULL,
   apellido_p VARCHAR (50) NOT NULL,
   apellido_m VARCHAR (50) NOT NULL,
   rol VARCHAR (8) NOT NULL,
   tipo VARCHAR (7) NOT NULL
);
```

```
CREATE TABLE movements(
   numero INT NOT NULL,
   fecha VARCHAR (10) NOT NULL,
   entregas INT NOT NULL,
   cubrio BOOL NOT NULL,
   rol VARCHAR (8) NOT NULL,
   PRIMARY KEY(numero,fecha),
   CONSTRAINT fk_employees
      FOREIGN KEY(numero) 
	      REFERENCES employees(numero)
);
INSERT INTO movements VALUES(2, '2021-04-06', 10, true, 'Chofer');
```

```
CREATE OR REPLACE PROCEDURE sueldo_mensual_by_emp (IN emp INT8, IN emp_fecha Text, INOUT emp_sueldo TEXT)
LANGUAGE plpgsql 
AS $$    
DECLARE 
  var_rol employees.rol%type;
  var_tipo employees.tipo%type;
  var_bono INT8;
  var_sueldo INT8;
  var_entregas INT8;
  var_nomina DOUBLE PRECISION;
  var_despensa DOUBLE PRECISION;
BEGIN
  -- get rol
  SELECT rol INTO var_rol
  FROM employees 
  WHERE numero = emp;
  
  -- get tipo
  SELECT tipo INTO var_tipo
  FROM employees 
  WHERE numero = emp;
  
  -- assign bono to calculate sueldo
  IF FOUND THEN
     CASE var_rol
	 WHEN 'Chofer' THEN 
       SELECT COUNT(*)*8*(30+10) INTO var_bono
       FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7);
	   var_sueldo = var_bono;
	 WHEN 'Cargador' THEN 
       SELECT COUNT(*)*8*(30+5) INTO var_bono
       FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7);
	   var_sueldo = var_bono;
	 WHEN 'Auxiliar' THEN 
	   SELECT COUNT(*)*8*(30+10) INTO var_bono
	   FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7) 
	   AND cubrio = TRUE AND rol = 'Chofer';
	   var_sueldo = var_bono;
	   SELECT COUNT(*)*8*(30+5) INTO var_bono
	   FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7) 
	   AND cubrio = TRUE AND rol = 'Cargador';
	   var_sueldo = var_sueldo + var_bono;
	   SELECT COUNT(*)*8*(30+0) INTO var_bono
	   FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7) 
	   AND cubrio = FALSE;
	   var_sueldo = var_sueldo + var_bono;
	 ELSE var_sueldo = 0;
	 END CASE;
  END IF;
  
  -- get entregas
  SELECT SUM(entregas)*5 INTO var_entregas
  FROM movements 
  WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7);
  
  --Calculate despensa y nomina
  IF var_tipo like 'Interno' THEN
     var_despensa = (var_sueldo + var_entregas)*0.4;
     var_nomina = (var_sueldo + var_entregas)*0.96;
  ELSE
     var_despensa = 0;
     var_nomina = var_sueldo + var_entregas;
  END IF;
  
  --Descontar ISR
  IF var_nomina > 16000 THEN
     var_nomina = var_nomina * 0.88;
  ELSE
     var_nomina = var_nomina * 0.91;
  END IF;
 
 emp_sueldo = CONCAT ('Nomina: ', var_nomina, ' Despensa: ', var_despensa, ' Total: ', (var_nomina+var_despensa));
 --emp_despensa = var_despensa;
 RETURN;  
END ;$$;

call sueldo_mensual_by_emp(2, '2021-04-06', '');
```


> **Nota:** Esta es otra opcion(Working).
```
CREATE OR REPLACE PROCEDURE nomina_by_emp (INOUT emp INT, IN emp_fecha Text)
LANGUAGE plpgsql 
AS $$
DECLARE 
  var_rol employees.rol%type;
  var_bono INT;
  var_sueldo INT;
  var_entregas INT;
BEGIN
  -- get the rol
  SELECT rol INTO var_rol
  FROM employees 
  WHERE numero = emp;
  
  -- assign bono to calculate sueldo
  IF FOUND THEN
     CASE var_rol
	 WHEN 'Chofer' THEN 
       SELECT COUNT(*)*8*(30+10) INTO var_bono
       FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7);
	   var_sueldo = var_bono;
	 WHEN 'Cargador' THEN 
       SELECT COUNT(*)*8*(30+5) INTO var_bono
       FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7);
	   var_sueldo = var_bono;
	 WHEN 'Auxiliar' THEN 
	   SELECT COUNT(*)*8*(30+10) INTO var_bono
	   FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7) 
	   AND cubrio = TRUE AND rol = 'Chofer';
	   var_sueldo = var_bono;
	   SELECT COUNT(*)*8*(30+5) INTO var_bono
	   FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7) 
	   AND cubrio = TRUE AND rol = 'Cargador';
	   var_sueldo = var_sueldo + var_bono;
	   SELECT COUNT(*)*8*(30+0) INTO var_bono
	   FROM movements 
       WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7) 
	   AND cubrio = FALSE;
	   var_sueldo = var_sueldo + var_bono;
	 ELSE var_sueldo = 0;
	 END CASE;
  END IF;
  
  -- get entregas
  SELECT SUM(entregas)*5 INTO var_entregas
  FROM movements 
  WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7);
  
 emp = var_sueldo;
 RETURN;
END ;$$;

call nomina_by_emp(2, '2021-04-06');
```

> **Nota:** Esta es otra opcion(Working).
```
CREATE OR REPLACE PROCEDURE nomina_by_emp (INOUT emp INT, IN emp_fecha Text)
LANGUAGE plpgsql 
AS $$
DECLARE 
  var_rol employees.rol%type;
  var_bono INT;
  var_sueldo INT;
  var_entregas INT;
BEGIN
  -- get the rol
  SELECT rol INTO var_rol
  FROM employees 
  WHERE numero = emp;
  
  -- assign the bono
  IF FOUND THEN
     CASE var_rol
	 WHEN 'Chofer' THEN var_bono = 10;
	 WHEN 'Cargador' THEN var_bono = 5;
	 WHEN 'Auxiliar' THEN var_bono = 0;
	 ELSE var_bono = 0;
	 END CASE;
  END IF;
  
  -- get sueldo + bono
  SELECT COUNT(*)*8*(30+5) INTO var_sueldo
  FROM movements 
  WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7);
  
  -- get entregas
  SELECT SUM(entregas) INTO var_entregas
  FROM movements 
  WHERE numero = emp AND SUBSTRING(fecha,1,7) like SUBSTRING(emp_fecha,1,7);
  
 emp = var_bono;
 RETURN;
END ;$$;

call nomina_by_emp(5, '2021-04-06');
```