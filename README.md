# Agencia de Viajes - Microservicios <code><img height="20" alt="spring" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"></code>
Este proyecto implementa una aplicación para una agencia de viajes utilizando una arquitectura basada en microservicios. El backend consta de cuatro microservicios principales: hoteles, vuelos, reservas y gestión de clientes. Además, incluye vistas para el login y la gestión de reservas.

## Descripción del Proyecto
Este proyecto tiene como objetivo crear una aplicación para una agencia de viajes que permite gestionar hoteles, vuelos, reservas y clientes. Cada funcionalidad está implementada como un microservicio separado, lo que facilita el mantenimiento y la escalabilidad del sistema.

## Microservicios
### Servicio de Hoteles
Este servicio gestiona la información relacionada con los hoteles.

Recursos:

**GET** /hoteles -> Devuele todos los hoteles<br />
**GET** /hoteles/{id} -> Devuelve los datos de un hotel a partir de su id<br />
**GET** /hoteles/pais/{pais} -> Devuelve la lista de hoteles de ese pais.<br />
**GET** /hoteles/disponibles -> Devuelve la lista de hoteles disponibles.<br />
**GET** /hoteles/disponibles/{id} -> Devuelve true o false dependiendo si ese hotel está disponible<br />
**GET** /hoteles/{nombre} -> Devuelve los datos de un hotel a partir de su nombre.<br />

Modelo de Hotel:

idHotel (autonumérico)  
nombre  
localizacion  
pais  
categoria  
precio  
disponible (sí/no)  

Testing: http://localhost:8080

### Servicio de Vuelos
Este servicio gestiona la información relacionada con los vuelos.

Recursos:

**GET** /vuelos -> Devuelve todos los vuelos  
**GET** /vuelos/{id} -> Devuelve el vuelo con ese id  
**GET** /vuelos/plazas/{plazas} -> Devuelve la lista de vuelos disponibles que tienen suficientes plazas para la cantidad especificada  
**PUT** /vuelos?id={id}&plazas={plazas}  Actualiza las plazas disponibles de un vuelo. Recibe el id del vuelo y las plazas reservadas  

Modelo de Vuelo:

idVuelo (autonumérico)  
companhia  
fechaVuelo  
origen  
destino  
precio  
plazasDisponibles  

Testing: http://localhost:8081

### Servicio de Reservas
Este servicio gestiona las reservas de los clientes.

Recursos:

**GET** /reservas -> Devuelve una lista de todas las reservas  
**GET** /reservas/{id} -> Devuelve la reserva del id indicado  
**POST** /reservas -> Crea una nueva reserva. Recibe un objeto JSON con el id del vuelo, id del hotel, id del cliente y               total de personas. Actualiza las plazas disponibles en el servicio de vuelos.  
**DELETE** /reservas/{id} -> Elimina una reserva si existe alguna con ese id  
**GET** /reservas/hotel/{nombre} -> Devuelve las reservas existentes para el nombre del hotel especificado. Interactúa con           el servicio de hoteles para obtener el id del hotel.  

Modelo de Reserva:

idReserva (autonumérico)  
idCLiente  
idHotel  
idVuelo  
numPersonas  

Testing: http://localhost:8082

### Servicio de Gestión de Clientes
Este servicio gestiona la información de los clientes y las operaciones de autenticación.

Recursos:
**GET** /clientes -> devuelve una lista de todos los clientes  
**GET** /clientes/{id} -> devuelve el cliente del id especificado  
**POST** /clientes -> Crea un cliente. Recibe un objeto JSON con todos los parametros menos id  
**DELETE** /clientes/{id} -> Elimina el cliente con el id especificado  

Modelo de Cliente:

id (autonumérico)  
user  
password  
nombre  
apellidos  
dni  

Testing: http://localhost:8083


# Interfaz
Para acceder al login de la interfaz la url es: **localhost:8083/cliente/login**  

### Login
![Alt text](img/login.png?raw=true "Login")

### Registro
![Alt text](register.png?raw=true "Registro")

### Numero de personas para el alta de reserva
![Numero](img/numPersonas.png?raw=true "Numero personas")

### Lista de reservas del usuario logeado
![Alt text](img/list.png?raw=true "Lista reservas")

### Seleccion vuelo con filtrado
![Alt text](img/vuelos.png?raw=true "Vuelos")

### Seleccion hoteles del origen del vuelo seleccionado
![Alt text](img/hotels.png?raw=true "Hoteles")


## Authors

- [@RaulRodal](https://www.github.com/raulrodal)


## Features

- Creacion de 4 microservicios
- CRUD Reservas
- Login
- Interoperabilidad entre los microservicios para realizar las operaciones con actualizaciones automáticas





[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)]([https://www.linkedin.com/](https://www.linkedin.com/in/raulrodal/))
