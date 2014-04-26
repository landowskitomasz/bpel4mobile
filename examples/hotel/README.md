BPEL 4 MOBILE - HOTEL EXAMPLE
===========
This is implementation of single process responsible for room clean up management. At the begining we will start with designing BPMN hotel check out process to put our example in real world context. 

![Hotel check out BPMN process](https://github.com/landowskitomasz/bpel4mobile/tree/master/examples/hotel/readme-assets/hotelCheckOutProcess.png "Hotel check out BPMN process")

At the picture you can see interaction beetween hotel customer and receptionist. At the begining customer leave the room and go to the reception, then check out. Receptionist look for client billings and ask him for payment. When everything goes well receptionis confim customer check out and starts clean up process to make room ready for next customer. This is where our example starts. 

At the BPMN process there are three subprocesses but only one is interesting for us, I mean 'Room clean up process'. 

![Room clean up process](https://github.com/landowskitomasz/bpel4mobile/tree/master/examples/hotel/readme-assets/roomCleanUpProcess.png "Room clean up process")