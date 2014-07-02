BPEL 4 MOBILE - HOTEL EXAMPLE
===========

This example demonstrates how to use bpel4mobile project to implement hotel room clean up process. The process is quite simple and everyone can understand it. There are a few actors in this example and the process 'implements' interaction between them. 

### BPMN 

I would like to start with designing simple diargram using BPMN 2.0 notation. Actually I will create two diagrams, one for describe hotel check out process which starts second process (this will be second diagram) responsible for room clean up.

At the begining we will start with designing BPMN hotel check out process. 

![Hotel check out BPMN process](/examples/hotel/readme-assets/hotelCheckOutProcess.png?raw=true)

At the picture you can see interaction between hotel customer and receptionist. At the begining customer leave the room and go to the reception, then check out. Receptionist look for client billing and ask him for payment. When everything goes well receptionist confirm customer check out and starts clean up process to make room ready for next customer. This is where our example starts. 

At the BPMN process there are three subprocesses but only one is interesting for us I mean 'Room clean up process'. 

![Room clean up BPMN process](/examples/hotel/readme-assets/roomCleanUpProcess.png?raw=true "Room clean up BPMN process")

This process is quite simple. There are four actors in this process: Hotel Management System, Cleaning service, Supervisor, Cleaning leady, each actor is represented by each pool. First one, 'Hotel Management System' in our example is only simple UI where receptionist mark the room to clean up. Next actor - cleaning service, is responsible for receiving clean up request from Hotem Management System and delivering it to cleaning lady and when cleaning lady finishes room clean up then supervisor will verify it. Last two actors, are people actors and in our example will work with Android mobile applications.

### BPEL 

To assume, we already have defined the flow of our clean up process, it is time to make most important decision - we will choose technologies to implement it. It is not surprising that almost every hotel already use some software to manage client check in and check out, probably creating a such software (clean up management), hotel management system will be already created. From other side there is need to deliver android applications to cleaning leadys and supervisors to improve their work. The conclusion is that we need some integration tools what can cooperate with buisness applications and with mobile applications. Here we are, BPEL - great integration tool with realy reach interface that can implement very complicated buisness logic and bple4mobile which provide middleware between BPEL and mobile apps. 

Great we already made a decision, what next?

The next step is defining sub projects which all together will implement whole process. 

* Hotel management system - This will be simple web application which will provide user interace to start room clean up, this application will also show whole process result. 

* BPEL process - this will be one peace of implementation of clean up service responsible for recieving clean up request from hotel management system and also will communication with mobile middleware . 

* BPEL 4 PEOPLE middleware - the second peace of hotel clean up service, responsible for delivering clean up tasks to people. 

* Android application - this will be the Supervisor and Cleaning Lady application. It will recive list of rooms to clean up and allove users to perform clean up or verify operations. 

