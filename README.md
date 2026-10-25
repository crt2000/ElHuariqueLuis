Instalación: Asegúrate de tener instalado MySQL 8 (o MariaDB/XAMPP) en tu computadora.   
Variable de entorno: Define la variable de entorno DB_PASSWORD con tu contraseña de MySQL antes de arrancar (por ejemplo, en PowerShell: $env:DB_PASSWORD="tu_clave"). Si tu usuario root no tiene contraseña, puedes dejarla vacía.
Ejecución: Ejecuta el proyecto mediante la terminal utilizando el comando del Maven Wrapper:   Bash.\mvnw.cmd spring-boot:run
