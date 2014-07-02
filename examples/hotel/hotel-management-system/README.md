BPEL 4 MOBILE - HOTEL EXAMPLE - HOTEL MANAGEMENT SYSTEM
===========
This is  simple web application which provide user interface to start room clean up, this application also shows whole clean up process result.

### GENERAL FLOW

This application consists of two views, first one provide simple html form which starts process and save new clean up task to database, and second view, which shows list of all created tasks with status (started, completed with success, completed with failure). This application communicates with BPEL process using it access point web service, and provide another web service to receive callback from BPEL process. 

### TECHNOLOGIES

Application is implemented with using Java, maven, Spring MVC framework, Bootstrap framework, Spring WS, Hibernate and H2 in memory database. 

### IMPLEMENTATION

Whole implementation begins in web.xml file which is typical for all java web applications. In this file there are two servlets defined. Both servlets are using Spring Framework. First one - dispatcher servlet - is specific for spring MVC, this is actually access point for all request provided by users. This requests are redirected to controllers by dispatcher servlet. Second servlet is specific for Spring WS and in our example is responsible for BPEL process callback web service. 

Further configuration files worth to mention are context files. That files are also specific for spring framework and contains spring configuration. There are for example properties that defines packages to scan by framework to find controllers and services. 

To save clean up requests application use in memory database, it means that when application is closed whole data is lost. I intentionally choose this type of database to limit all configuration to just start web application. configuration of database is provided by persistence.xml. This file is typical for hibernate.   

As I recently wrote in web.xml there is dispatcher servlet defined, which redirects user request to controllers. Generally when user wrote url to our application in the browser, the browser will send HTTP GET request which will be handled by dispatcher servlet. This servlet will find destination controller using it RequestMaping annotation. Controller receives the request, and will respond with html page. In this application there is only one controller, called MainController. This controller provide three methods, one for show list of request from database, second one for show form to start process and last one to handle form POST request. 

List and form are not really interesting ,they just gets some data from database and responed with html pages. More interesting method is one that handle form request. This method gets data from http request and invokes startProcess method from CleanUpService class. 'startProcess' method, prepare data to send, serialize it to XML using Jaxb2Marshaller and invokes BPEL process access point web service using WebServiceTemplate class provided by Spring framework. 

Last thing which need to be cleaned is implementation of receiving callback from BPEL process. As I mentioned before in web.xml there is also spring-ws servlet. This servlet works really similar to dispatcher servlet, it handle http requests and redirect them to web service endpoint class. In our example there is only one end point defined - CleanUpServiceCallbackEndpoint. In this class there is one method implemented called handleHolidayRequest, this method handles the web service request, in few words, this method reads data received by web service and change clean up task status. 

### HOW TO RUN IT

In this example I use maven - the tool that automate application build process. To build application all you have to do is installing maven on your machine and run following command in console from project directory. 

```python
mvn clean install
```

This command will build war file in 'target' directory. Then you can use this file to run application using any lightweight java application server like tomcat. There is also another way to start application, you can simple run following command from console, which will download jetty application server to your computer and run it on port 8282. 

```python
mvn jetty:run -Djetty.port=8181
```

Application will be avalable from url:

```python
http://localhost:8181/hotel-management-system/list.html
```

This is really important to start application on port 8181 because further applications created in hotel example will look for Hotel Management System on this port.  

