# Evolvice Task implemented by Taha On Friday 26-Oct-2018

# How to Build
  - Download latest maven version from https://maven.apache.org/download.cgi
  - Follow instruction to be able to run 'mvn' commands.
  - If you are using windows you need to add bin folder to the System path variable.
  - Run 'mvn clean install' to run test-cases, generate the jar.
# How to Run
  - Install jdk 8 in your machine.
  - If you are using windows you need to add bin folder to the System path variable.
  - The Database will start automatically after running next command.
  - Run 'java -jar target\EvolviceTask.jar'
  - After running the application you can see the logs in '/logs/EvolviceTask.log'
  
# By default, tomcat will take port 8080, so you can call 
  - GET		'http://localhost:8080/car/all' to see all available cars in the Database.
  - PATCH	'http://localhost:8080/car/update' to update existing car in the Database but you have to add Car DTO in request body.
  - POST	'http://localhost:8080/car/new' to add new car in the Database but you have to add Car DTO in request body.
  - DELETE	'http://localhost:8080/car/delete/{carId}' to delete car by Id in the Database.