# reto-stefanini

1. Clonar repositorio
2. Ejecutar < docker compose up > en el directorio
   1. dentro del compose tenemos:
      1. app (port=1001)
      2. postgres (port=5432)
      3. pgadmin (port=5050)
3. pgadmin: http://localhost:5050/

usuario: admin@patito.com

clave: admin

al crear un nuevo server, usar como host: db_stefanini, clave: postgres, usuario: postgres

ya debe existir una base de datos: stefanini_db

4. Swager:
   http://localhost:1001/swagger-ui/index.html#/
cada uno de los metodos del swager se explica.
   1. para la opcion 3, usar el archivo proporcionado "Consulta1.csv" cuenta con 11k registros, y la estructura necesaria.
   2. la informacion almacenada debe quedar en la tabla reparaciones
