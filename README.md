To Run this Project Follow the steps below

Step 1: Navigate to docker/common in the card-scheme project root directory
    
        cd docker/common

Step 2: Run docker-compose up -d to start the project.

        docker-compose up -d
        

Alternatively, From the  card-scheme project root directory run the following command.
Step 1: Run mvn clean package  -DskipTests docker:build
        
        mvn clean package  -DskipTests docker:build
        
        
Step 2: To Build the card-scheme-consumer image, do the following

Run mvn clean package -DskipTests docker:build from the card-scheme-consumer root project directory
  
        mvn clean package  -DskipTests docker:build
       

Step 3 : Navigate to docker/common in the card-scheme project root directory

        cd docker/common
        
Step 4: run docker-compose up -d

        docker-compose up -d
        
Step 5: A simple UI for the endpoints and display the output properly

        http://localhost:9190/swagger-ui.html
        
