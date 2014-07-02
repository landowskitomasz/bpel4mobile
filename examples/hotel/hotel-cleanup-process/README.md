BPEL 4 MOBILE - HOTEL EXAMPLE - CLEAN UP BPEL PROCESS
===========
This is one peace of implementation of clean up service responsible for receiving clean up request from hotel management system and also communication with mobile middleware. 

### GENERAL FLOW

In main description of hotel example you can find that we use BPEL as integration tool that will integrate hotel management system and android applications. Because mobile applications can't provide web services by themselves there is another extra layer called mobile middleware. 

All flow in this case starts from Hotel Management System which calls access point web service. Hotel Management System will send in that request all data need to perform clean up, like: room number, floor etc. At the beginning process will create clean up task for cleaning lady and will wait for it completion. When cleaning lady finish cleaning, process will create another task for supervisor, to let him accept or dismiss cleaning. The last two steps will repeat until cleaning will be confirmed by supervisor then callback will be invoked.  

### TECHNOLOGIES

In this module there is one technology used - BPEL.

### IMPLEMENTATION
// TODO: 

## CORRELATION
// TODO: 

## SCOPE
// TODO: 

### HOW TO RUN IT

Process is created base on BPEL specification and can be deployed to most of BPEL engines. I test it only on Apache ODE engine and it is recommended to run it on this platform. There is also one file in that project typical for Apache ODE. 

To run process on recommended platform you have to download tomcat and Apache ODE engine. Then deploy Apache ODE on tomcat started on port 8080. Apache ODE provides user interface which will be available on url.

```python
http://localhost:8080/ode
```

Using this interface you can simply deploy zip file with all files located in bpelContent directory. When deploy finish with success, process will be up and ready to use by hotel management system. 



