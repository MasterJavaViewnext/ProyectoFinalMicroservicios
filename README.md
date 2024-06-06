Agencia de Viajes - Microservicios
Este proyecto implementa una aplicación para una agencia de viajes utilizando una arquitectura basada en microservicios. El backend consta de cuatro microservicios principales: hoteles, vuelos, reservas y gestión de clientes. Además, incluye vistas para el login y la gestión de reservas.

Tabla de Contenidos
Descripción del Proyecto
Microservicios
Servicio de Hoteles
Servicio de Vuelos
Servicio de Reservas
Servicio de Gestión de Clientes
Vistas
Instalación
Uso
Contribución
Licencia
Descripción del Proyecto
Este proyecto tiene como objetivo crear una aplicación para una agencia de viajes que permite gestionar hoteles, vuelos, reservas y clientes. Cada funcionalidad está implementada como un microservicio separado, lo que facilita el mantenimiento y la escalabilidad del sistema.

Microservicios
Servicio de Hoteles
Este servicio gestiona la información relacionada con los hoteles.

Recursos:

GET /hoteles/disponibles: Devuelve la lista de hoteles disponibles.
GET /hoteles/{nombre}: Devuelve los datos de un hotel a partir de su nombre.
Modelo de Datos:

idHotel (autonumérico)
nombre
categoria
precio
disponible (sí/no)
Servicio de Vuelos
Este servicio gestiona la información relacionada con los vuelos.

Recursos:

GET /vuelos/disponibles?plazas={totalPlazas}: Devuelve la lista de vuelos disponibles que tienen suficientes plazas para la cantidad especificada.
PUT /vuelos/{idVuelo}/reservar: Actualiza las plazas disponibles de un vuelo. Recibe el id del vuelo y las plazas reservadas.
Modelo de Datos:

idVuelo (autonumérico)
compañia
fechaVuelo
precio
plazasDisponibles
Servicio de Reservas
Este servicio gestiona las reservas de los clientes.

Recursos:

POST /reservas: Crea una nueva reserva. Recibe un objeto JSON con el id del vuelo, id del hotel, nombre del cliente, DNI y total de personas. Actualiza las plazas disponibles en el servicio de vuelos.
GET /reservas?hotel={nombreHotel}: Devuelve las reservas existentes (nombre, DNI, vuelo) para el nombre del hotel especificado. Interactúa con el servicio de hoteles para obtener el id del hotel.
Modelo de Datos:

idReserva (autonumérico)
nombreCliente
dni
idHotel
idVuelo
Servicio de Gestión de Clientes
Este servicio gestiona la información de los clientes y las operaciones de autenticación.

Recursos:

POST /clientes/login: Permite a los clientes iniciar sesión.
POST /clientes/registro: Permite a los nuevos clientes registrarse.
Modelo de Datos:

idCliente (autonumérico)
nombre
email
contraseña
Vistas
La aplicación incluye vistas para que los clientes puedan iniciar sesión y realizar reservas. Estas vistas interactúan con los microservicios para proporcionar una experiencia de usuario completa.

Instalación
Clonar el repositorio:
bash
Copiar código
git clone https://github.com/tu-usuario/agencia-viajes-microservicios.git
