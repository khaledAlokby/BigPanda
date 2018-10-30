# BigPanda
Requirment to Run the project : Linux OS, jre.
To run the project, please download the project and import it to java IDE and run it(I have unsolved issue with including the resources into the jar, after i solve it the procedure will change to:
mvn clean package
then run
java -jar target/(the jar file) )



# Three thing to improve:
1 )Implement the while in the consumer/que with the observer pattern in the reaciveX proggraming.
2 ) Change the cashes (maps) with a LRU michanism and connect it with DB like aws DynamoDB.
3 ) make the que(Observable) work with multible workers(consumers)
