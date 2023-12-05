# Prueba técnica para Avoris creada por Fernando Garcia Doval

La prueba consiste en crear una aplicación web que implemente dos servicios REST.
Los detalles y el enunciado de la prueba se pueden encontrar en *challenge/src/main/resources/Prueba_técnica_Senior_Software_Engineer.pdf*

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.1.4
- Kafka
- JUnit 5
- MongoDB

## Forma de despliegue

1. En *challenge/compose* se encuentra el docker-compose.yml que despliega toda la aplicacion, basta con ejecutar *docker compose up* en dicha ruta.
2. Para no depender de Postman o cualquier otro cliente REST he utilizado la dependencia *springdoc-openapi-starter-webmvc-ui* que te levanta en *http://localhost:8088/avoris/swagger-ui/index.html* una interfaz gráfica para usar ambos endpoints
3. Si por el contrario se prefiere usar un cliente REST se puede atacar a http://localhost:8088/avoris (/search o /count)
4. Para parar la aplicación simplemente parar el docker compose

## Forma de testing

1. Para este proyecto se han realizado tests de integración end2end usando Testcontainers, para ejecutar correctamente los tests es necesario ejecutarlos en una maquina con el servicio de docker corriendo y disponible. En caso de linux y mac es necesario poder utilizar los comandos docker sin necesidad de usar sudo.
2. Ejecutar *mvn test*

## Explicación de las decisiones tomadas y aclaraciones

- Se ha desarrollado toda la prueba dentro de un mismo proyecto Maven para simplificar aun siendo dos servicios completamente diferenciados los que se piden.

## Puntos de mejora

- Dividir el proyecto en dos proyectos distintos, esto es sencillo ya que los archivos no tienen acoplamiento, aparte de que resulta raro tener un productor y un consumidor de kafka de un mismo topico en una misma app, se podría crear una para /search y otra para /count tambien.