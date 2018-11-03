# BigPanda
Requirment to Run the project : Linux OS, jre, maven.

To run the project, please download the project, 
download the json generator for Linux OS Then run:
1) navigate to the project --> cd (project path)
2) mvn package
after the project compile successfully run
2) java -jar target/task-1.0-SNAPSHOT.jar (path to the generator resource) 
for example :
    java -jar target/task-1.0-SNAPSHOT.jar /home/khald/Documents/BigPanda/src/main/resources/generator-linux-amd64


#API
After the project run successfully, in the browser type :
1) hostname:portNumber/eventCount?type=foo to get the count of foo(or bar or any event_type)
that appears in event_type.
for example : http://localhost:5001/eventCount?type=bar

2) to get the data count of a specific data navigate to the URL above with 
RequestMapping /dataCount?data=lorem, for example: http://localhost:5001//dataCount?data=lorem.

# Three thing to improve:
1 )Implement the while in the consumer/que with the observer pattern in the reaciveX proggraming.
2 ) Change the cashes (maps) with a LRU mechanism and connect it with DB like aws DynamoDB.
3 ) make the que(Observable) work with multiple workers(consumers)
