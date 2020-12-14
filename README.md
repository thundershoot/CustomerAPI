# CustomerAPI

# Objective
A REST API prototype using Spring Boot to register Customers.

# Characteristics
- Authentication Endpoint> / api / auth
- CRUD Customer Endpoint> / apit / customer
- Endpoint Admin User CRUD> / api / user
Note: only registering client and authentication are open for authentication.
Verbs used: POST, PUT, GET and DELETE, import Customer API.postman_collection.json into Postman for API manipulation.

# Dependency
- MongoDB server, use docker-compose mongo.yml
- check application.properties> spring.data.mongodb variables.
- Create two Collections: user, customer
- JDK8, SpringToolSuite4.
- Project made in SpringBoot

# Run
- Import the project into SpringToolSuite4 as a maven project
- Run the docker-compose service with mongo.yml, in the first run it creates the user and customer collections.
- Right click on the project folder> Run as> Spring Boot App.
- Use Postman to manipulate the API. Note: in the postman after authenticating use the user's token variable in the {{token}} variable defined as global in the postman.
