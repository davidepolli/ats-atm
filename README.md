# ATS - ATM Test
This document purpose is give a brief description of the web application ats-atm
## Overview
The goal of the application is to provide a REST service exposing methods to retrieve and filter a list of ATMs
and a front-end application that calls the REST methods and displays the ATMs.

The REST API must provide:
- a method for retrieve ATMs for a given city
- a method for searching a keyword in the ATM's fields

The front-end application must provide a web page diplaying:
- a form calling the REST method passing a city
- a search box 
- the list of the ATMs obtained


For the development the following tools has been used:
* Eclipse Oxygen.3a Release (4.7.3a)
* Spring Framework  4.3.26
* Spring security 4.2.14
* Apache Tomcat 7.0.103
* Apache Maven 3.1.1
* JDK 1.8.0_171

The JSON containing all the ATMs is available at :
```sh
https://www.dropbox.com/s/6fg0k2wxwrheyqk/ATMs?dl=1
```

## Configurations

The configuration of the environment is done using Spring Java configuration file and Spring dispatcher-servlet XML file:
```sh
it.noema.training.spring.springrestdemo.config.DemoAppConfig
```
For perfomance reasons the complete List of ATMs is retrieve from Dropbox at startup and inject in the spring container.
To retrieve the ATMs the Spring **RestTemplate** utility class has been used coupled with Jackson libraries for JSON conversion.

# Rest API
To provide the REST service the Spring-MVC **@RestController** annotation has been used.
The **ATMRestController** class access and elaborate the List of ATMs and providess 3 REST methods. 
```sh
GET http://localhost:8080/evaluation-poc-back-end/api/ATMs/
```
Retrieve all the ATMs

```sh
GET http://localhost:8080/evaluation-poc-back-end/api/ATMs/{cityName}
```
Retrieve the ATMs filtered by city

```sh
GET http://localhost:8080/evaluation-poc-back-end/api/ATMs/{cityName}/{searchCriteria}
```
Retrieve the ATMs filtered by city and a search criteria.

The URL of the REST methods has been stored in the **application.properties** file to be easily modified (for example to chenge the server port) and retrieved using the Spring **Environment** class.

For the search **Java 8 streams** has been used to scan the ATM's fields searching for a match with the criteria;

The implementation of the methods has been encapsulated behind a generic ATMService interface and can be replaced if needed(for example retrieve the ATMs from a database).
The service implementation **ATMServiceIMPL** has been injected into the Spring context using the **@Service(value = "clientAtmService")** annotation.
This class has the reponsability to retrieve the full ATM List (injected using th Spring **@Autowired** annotation) and return the filtered list.
# Front End Application

The front-end application provide a jsp page and a Spring MVC web controller calling the REST services provided
The implementation of the methods has been encapsulated behind a generic ATMService interface, the controller is not aware of the source of the List.
The service implementation **ATMServiceIMPL**  has been injected into the Spring context using the **@Service(value = "clientAtmService")** annotation.
This class has the reponsability to call the REST services (still using **RestTemplate** class) and return the filtered list to the controller.
For displaying the ATM List **JSTL** tag-libraries has been used.

For the view layout, apache tiles has been used.
I18N has been configurated for use of the basename 'messages', the resource files are located under 'src\main\resources\'

