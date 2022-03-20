# LABORATORIO MODULARIZACIÓN CON VIRTUALIZACIÓN: DOCKER Y A AWS

1. El servicio MongoDB es una instancia de MongoDB corriendo en un container de docker en una máquina virtual de EC2
2. LogService es un servicio REST que recibe una cadena, la almacena en la base de datos y responde en un objeto JSON con las 10 ultimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.
3. La aplicación web APP-LB-RoundRobin está compuesta por un cliente web y al menos un servicio REST. El cliente web tiene un campo y un botón y cada vez que el usuario envía un mensaje, este se lo envía al servicio REST y actualiza la pantalla con la información que este le regresa en formato JSON. El servicio REST recibe la cadena e implementa un algoritmo de balanceo de cargas de Round Robin, delegando el procesamiento del mensaje y el retorno de la respuesta a cada una de las tres instancias del servicio LogService.

## Arquitectura:

![](img/arq.png)
	

## Cómo empezar

A continuación podrá encontrar los pasos ccon los cuales puede acceder al sitio web o usar el proyecto de manera local.

### Prerrequisitos


* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [Java ](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html)       Ambiente de desarrollo
* [Git](https://git-scm.com/) - Sistema de control de versiones

### Instalación


Para descargar el proyecto ejecute 

   git clone https://github.com/ZulyVargas/AREP_AWS_DOCKER.git

## Documentación
Para generar la documentación se debe ejecutar:

    mvn javadoc:javadoc

Esta quedará en la carpeta target/site/apidocs :

![](img/javadocs.png)

O diríjase a la siguiente dirección: 
* [Documentación]()

## Construido con 

* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [Heroku](https://heroku.com) - Plataforma de despliegue

## DESARROLLO PASO A PASO

1. Para realizar los contenedores correspondientes es necesario primero que todo crear los Dockerfile para cada servicio.
2. Luego de creados los servicios se crean las imagenes con el comando **docker build**:

LBRoundRobin:

    docker build --tag awsdocker/roundrobin .

![](img/rr-docker.png)


LogService:

Para este ejercicio se crearan 3 instancias para LogService. Primero se construye la imagen:
    
    docker build --tag awsdocker/logservice .

![](img/logservices.png)

Se crea el archivo docker-file.compose para crear los contenedores correspondientes:

![](img/compose.png)

Verificamos en Docker los contenedores creados:

![](img/docker.png)

![](img/lbservice.png)

![](img/mensajes.png)

Se ingresa un nuevo mensaje:

![](img/nuevo.png)

Se verifica que se recargan las cadenas ingresadas:

![](img/nuevoOk.png)



## Autores

**Zuly Valentina Vargas Ramírez** 


## Licencia

Este proyecto esta bajo la licencia GNU(General Public License) los detalles se encuentran en el archivo [LICENSE](LICENSE.txt).
