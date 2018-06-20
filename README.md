# Music Collection

## Sobre el repositorio

Este repositorio contiene el código que modela el problema presentado [aquí](https://github.com/ohierro/tecnara/tree/master/apps/music_collection). El mismo fué implementado haciendo uso del patrón Modelo-Vista-Controlador.

* Para el desarrollo de este proyecto, se escogió Spring Boot como framework de desarrollo y PostgreSQL como base de datos. 

* Las vistas del mismo fueron implementadas haciendo uso de HTML, CSS y Bootstrap.

* Las instrucciones que se muestran a continuación, están dirigidas a sistemas basados en Debian, en particular, Ubuntu.

## Requerimientos

Los requerimientos básico de este proyecto son los siguientes:

* Java 8
* Una versión razonablemente moderna de PostgreSQL

### Instalación de dependencias:

Se pueden instalar las dependencias en cualquier versión reciente de Ubuntu con
los siguientes comandos:

* Oracle Java 8 :
```
sudo add-apt-repository ppa:webupd8team/java
sudo apt update
sudo apt install oracle-java8-installer
```
 Luego se deberán agregar la variable de entorno
 `JAVA_HOME="/usr/lib/jvm/java-8-oracle"`. Puedes agregarla a `.bashrc`
 (para un solo usuario) o en `/etc/environment` (global).

* PostgreSQL (útil para probar el servidor localmente):
```
sudo apt install postgresql
```
  Consulte la documentación del Postgres si quieres crear tu propia base de datos
  y usuario para probar el servidor.

* Maven (opcional, necesario para compilar pero no para ejecutar):
```
sudo apt install maven
```
### Creación de la base de datos

```bash
sudo -u postgres psql
create user music with password 'music';
create database collection owner music;
alter user music CREATEDB;
```

### Compilación y despliegue

Uno de los flags de hibernate que se encuentran en el archivo de application.properies, esta configurado como **"create-drop"**, esto quiere decir que la base de datos será eliminada y creada cada vez que se compile la aplicación.

La aplicación se puede compilar desde la línea de comandos con Maven. Esto se puede realizar ejecuntado el siguiente comando en el directorio raíz del proyecto:

```
mvn package
```

Esto generara un JAR en el directorio `target`

Para ejecutar el servidor, solo debe ejecutarse
`java -jar <JAR-generado> --server.port=<puerto>`

Luego de ejecutar el comando anterior se podrá acceder al sistema, ingresando en algún navegador el siguiente URL.
`localhost:<puerto>`

## Sobre el desarrollo

Se utilizó [NetBeans](https://netbeans.org/downloads/) para desarrollar el proyecto.
