filemanager api 
 
 
System Requirements:
1. Install Java 1.8
2. Install Maven 3.3.9
3. Install Postman
4. Set Environment variables for Java and Maven 
5. If port 8080 is already occupied then use application.properties file to configure your desired port with key name as "server.port"

How to Run: 
1. Download filemanager folder to your local environment.   
2. Open Command prompt and navigate to the saved directory
3. Run the command “mvn clean install”.
4. Navigate to the generated target directory and run the command
        “java –jar target/filemanager-0.0.1-SNAPSHOT.jar”.
5. open postman
to upload a file:
      url -  http://localhost:8181/filemetadata/v1/upload      
      http method -  POST
      remove headers	
      use form-data option to upload "sample.txt" with key name as "file"		

to get file meta data
    url -  http://localhost:8181/filemetadata/v1/metadata?filename=sample.txt    
    http method-  GET 
