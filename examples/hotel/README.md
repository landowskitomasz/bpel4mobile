BPEL 4 MOBILE - HOTEL EXAMPLE
===========
This is implementation of single process responsible for hotel room clean up management.

### BPMN 

At the begining we will start with designing BPMN hotel check out process to put our example in real world context. 

![Hotel check out BPMN process](/examples/hotel/readme-assets/hotelCheckOutProcess.png?raw=true)

At the picture you can see interaction beetween hotel customer and receptionist. At the begining customer leave the room and go to the reception, then check out. Receptionist look for client billings and ask him for payment. When everything goes well receptionis confim customer check out and starts clean up process to make room ready for next customer. This is where our example starts. 

At the BPMN process there are three subprocesses but only one is interesting for us I mean 'Room clean up process'. 

![Room clean up BPMN process](/examples/hotel/readme-assets/roomCleanUpProcess.png?raw=true "Room clean up BPMN process")

This process is quite simple. There are four actors in this process: Hotel management system, Cleaning service, Supervisor, Cleaning leady. Last two are people actors and in our example will work with Android mobile applications. First one, 'Hotel management system' is responsible for starting whole process, this is the place where receptionist mark the room to clean up. Last actor is responsible for delivering room clean up tasks to the mobile users and monitoring them. 

### BPEL 

BPMN is not implementations, this is only graphical description of the system. I this section we will extract from this description all sub-systems required to implement our concept, we will desing web services of that systems and preprare BPEL process that will comunicate with all of them. 

* Hotel management system - because customer and recptionist interactions are not realy interesting for as in this example, the first system will be Hotel managemrnt system. It will be responsible for room management and when room need to be cleaned will start clean up process. This system will have user interface for receptionist. Receptionis will have posibility to change room state and create room reservations. 

* BPEL process engine - 
