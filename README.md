# Aplicación de Lista de Tareas con Autenticación
## API RESTful que utiliza Spring Boot para gestionar una lista de tareas con autenticación de usuarios.   

### Funcionalidades
- La aplicación permite a los usuarios registrarse, iniciar sesión, agregar tareas, marcarlas como completadas y eliminarlas. 
- Implementa endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las tareas y sobre los usuarios.
- Almacena la información en una base de datos mysql.
- Implementa endpoints de registro e inicio de sesión para crear y autenticar usuarios.
- Utiliza bcrypt para almacenar de forma segura las contraseñas de los usuarios.
- Utiliza docker para ser ejecutada desde cualquier entorno.

#### Instrucciones para utilizar la aplicación desde el perfil de desarrollo (dev)
```
docker-compose up
```

#### Instrucciones para utilizar la aplicación con variables de entorno
Para esta opción se debe contar con un archivo env-file que contenga las variables de entorno y no sea público.
1. Crear la imagen de la aplicación
```
docker build -t tareas-prod:v1 .
```

2. Crear una red para que ambos contenedores puedan comunicarse
```
docker network create -d bridge tareas-net
```

3. Crear un contenedor con MySQL
```
docker run -d --name mysql-prod --net tareas-net --env-file example_env_file mysql
```

4. Crear el contenedor de la aplicación
```
docker run -it --name tareas-prod --net tareas-net -p 8080:8080 --env-file example_env_file tareas-prod:v1
```

