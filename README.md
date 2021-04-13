# Project 2

### ERD Diagram
![image](https://user-images.githubusercontent.com/7227339/114466739-7c7b4400-9bae-11eb-8087-0e3f1b19379e.png)

### Endpoints
| Endpoint | Functionality | Access |
|---|----| --- |
| GET /api/books | Get all Books | PUBLIC |
| POST /api/books/1 | Creating a Single Book | PUBLIC |
| PUT /api/books/1 | Updating Single Book | PUBLIC |
| DELETE /api/books/1 | Delete Single Book | PUBLIC |
| GET /api/books/1/authors | Get All Authors | PUBLIC |
| POST /api/books/1/authors/1 | Get a Single Author | PUBLIC |
| PUT /api/books/1/authors/1 | Update a Single Author | PUBLIC |
| DELETE /api/books/1/authors/1 | Delete a Single Author | PUBLIC
| GET api/books/1/genres | Get all Genres | PUBLIC |
| POST api/books/1/genres/1 | Create a Single Genre | PUBLIC |
| PUT api/books/1/genres/1 | Update a Single Genre | PUBLIC|
| DELETE api/books/1/genres/1 | Delete a Single Genre | PUBLIC |
| GET /api/books/1/publishers | Get all Publishers | PUBLIC |
| POST /api/books/1/publishers/1 | Create a Single Publisher | PUBLIC |
| PUT api/books/1/publishers/1 | Update a Single Publisher | PUBLIC |
| DELETE api/books/1/publishers/1 | Delete a Single Publisher | PUBLIC |

### Machineries used
- Spring Boot Framework
- IntelliJ IDEA/Java 11
- Tomcat Server
- Apache Maven 
- Dev Profile
- Postman
- Postgresql
- pgAdmin 4


### Dependencies
The following dependencies are listed in the POM.xml file.

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.4.4</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>


You can reload project under maven options if dependencies don’t load properly on first download.
![image](https://user-images.githubusercontent.com/7227339/114568739-03293300-9c3a-11eb-875e-096c82f199ab.png)



For the database, we are using postgreSQL. For our file to work out of the box, create a database in postgreSQL and name it ‘bookstore’. If you have a different password for your postgres user, change it in application-dev.properties.
![image](https://user-images.githubusercontent.com/7227339/114569211-64510680-9c3a-11eb-8765-9f9d7d0d8bad.png)


