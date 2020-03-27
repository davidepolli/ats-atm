
# ATS - ATM Test
This document purpose is give a brief description of the web application **ats-atm**
## Overview
The goal of the application is to provide a **REST** service exposing methods to retrieve and filter a list of ATMs
and a front-end application that calls the REST methods and displays the ATMs.

The REST API must provide:
- a method for retrieve ATMs for a given city
- a method for searching a keyword in the ATM's fields

The front-end application provides a web page displaying:
- a select dropdown populated with all the cities having at least one ATM, on selecting a city a search is performed for its ATMs 
- a search box for free search: on submit a search is performed on the ATMs properties
- as result of search, the list of the ATMs obtained is displayed in a table and a button that shows a modal with the JSON from the service is available

For accessing the application, due to spring security, the credentials to be used are
user: **davidep**
password: **123456**


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
it.noema.ats.config.ATMWebConfig
```
For perfomance reasons the complete List of ATMs is retrieved once from Dropbox at startup and stored in an **H2** database, in a JPA managed Entitiy called **ATM**,  persisted using the facilities provided by Spring Data. 

# Rest API
To provide the REST service the Spring-MVC **@RestController** annotation has been used.
The **APIController**  provides 3 REST methods. 
```sh
http://localhost:8080/ats-atm/api/atm/all
```
Retrieve all the ATMs

```sh
http://localhost:8080/ats-atm/api/atm/city/{cityName}
```
Retrieve the ATMs filtered by city

```sh
http://localhost:8080/ats-atm/api/atm/filter?keyword={keyword}
```
Retrieve the ATMs filtered by city and a search criteria.

The implementation of the methods has been encapsulated behind a generic ATMService interface and can be replaced if needed.
The service implementation **ATMServiceIMPL** has been injected into the Spring context using the **@Autowired** annotation.
This class delegates to the **ATMRepository** interface, injected too via autowiring, for the execution of the query against the ATM via **JPA** and Spring Data method naming convention.
As example,  the **findByCityContainingIgnoreCase(String city)** that allows to retrieve ATMs by city, is just an interface method, no need to implement boilerplate code.

# Front End Application

The front-end application provided a jsp page, served by a Spring MVC web controller, this controller simply loads a list of cities having ATMs and provides it to the page using spring mvc **model**.

The page layout has been built using Apache tiles.
I18N has been configurated for use of the basename 'messages', the resource files are located under 'src\main\resources\'
The page shows a summary of the available REST services with example URL to use in a client, like POSTMAN, and two columns showing 2 differente features.
In the left column, a select is populated with the cities names and on selecting one city an AJAX call is fired to the REST services for listing city's ATMs, retrieved data is displayed in a table.
The right column shows an input field for a free search in the ATMs field (city, address etc), on submit another AJAX call is fired to the filtering REST service and the results are shown in the same table as the other UC.

The results table has a button allowing the user to see the raw JSON in a modal.

# Security
As a bonus, spring security has been enabled, securing the access to the app page and to the service call with baseauth.