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
