# Aplicación de Lista de Tareas con Autenticación
## API RESTful que utiliza Spring Boot para gestionar una lista de tareas con autenticación de usuarios.   

### Funcionalidades
- La aplicación permite a los usuarios registrarse, iniciar sesión, agregar tareas, marcarlas como completadas y eliminarlas. 
- Implementa endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las tareas y sobre los usuarios.
- Almacena la información en una base de datos mysql.
- Implementa endpoints de registro e inicio de sesión para crear y autenticar usuarios.
- Utiliza bcrypt para almacenar de forma segura las contraseñas de los usuarios.
- Utiliza docker para ser ejecutada desde cualquier entorno.

### Instrucciones para utilizar la aplicación desde el perfil de desarrollo (dev)  
Utilizando este perfil la base de datos se encuentra precargada con algunos datos para pruebas.  
```
docker-compose up
```

### Instrucciones para utilizar la aplicación con variables de entorno
Para esta opción se debe contar con un archivo env-file que contenga las variables de entorno y no sea público.  
Este perfil no cuenta con datos precargados.  
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

### Una vez inicializada la aplicación, se debe utilizar las siguientes rutas:  
En caso de estar usando el perfil de desarrollo (docker compose), se pueden usar los siguientes JSON para hacer pruebas con los datos precargados en la base de datos.  

#### Autenticación
+ Login: http://localhost:8080/auth/authenticate  
```
{
    "username": "lmarquez",
    "password": "clave123"
}
```
+ Registro: http://localhost:8080/auth/register
```
{
    "username": "pedroe",
    "password": "clave412",
    "email": "pedro@email.com"
}
```

##### Nota: Antes de pasar a los endpoints de usuarios y tareas es necesario autenticarse por medio del Bearer Token. 

#### Tareas
+ Crear una nueva tarea: http://localhost:8080/api/v1/tareas/nueva
```
{
    "description": "crear una autenticacion de usuarios",
    "dateline": "2024-04-12",
    "tag": "trabajo",
    "status": "pendiente"
}
```
+ Listar todas las tareas: http://localhost:8080/api/v1/tareas/lista
+ Buscar una tarea específica con base en el id: http://localhost:8080/api/v1/tareas/buscar/2
+ Editar una tarea: http://localhost:8080/api/v1/tareas/editar/3
```
{
    "description": "organizar la despensa",
    "dateline": "2024-04-10",
    "tag": "hogar",
    "status": "pendiente"
}
```
+ Borrar una tarea: http://localhost:8080/api/v1/tareas/borrar/1

#### Usuarios
+ Listar a todos los usuarios: http://localhost:8080/api/v1/usuarios/lista
+ Buscar un usuario específico con base en el id: http://localhost:8080/api/v1/usuarios/buscar/2
+ Editar un usuario: http://localhost:8080/api/v1/usuarios/editar/1
```
{
    "name": "luis1",
    "lastname": "marquez",
    "birthdate": "1992-07-13",
    "email": "lmarquez@correo.com",
    "username": "lmarquez",
    "password": "prueba"
}
```
+ Borrar un usuario: http://localhost:8080/api/v1/usuarios/borrar/1
