# Project 2 - BookAPI
### Description

A REST API for storing information about books

### General Approach

We chose to create three tables, which are all connected to a main table, called Book. Our approach was to create a standalone Book database with its own fields and then have the other fields populated by dependencies from the Author, Publisher, Genre tables. We split our code into business logic in the Service package and front-end mapping in the Controller package. We also created custom error messages contained within the Exception package, which are handling those cases. Our Repository package is using predefined methods from JPARepository interface. We also have our own custom methods, which aren’t included inside JPA. Our development process is going on inside a “dev” profile, defined in the Resource folder, which can be switched down the line.

### Descriptions of any unsolved problems or major hurdles

We didn’t experience any major obstacles during our base deliverable. We had small issues with the mapping of the tables, while trying to figure out how the XtoX mapping would be done between the Book, and the rest of the tables. We also had a time, where a typo in a wrapper class caused us to debug our code for some 15 minutes. Another small hurdle was figuring out how the unit testing syntax works.


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

### User Stories
Our Users are primarily library, bookstore, book depot employees, or software testers and software novices trying to understand the Spring framework.

User stories:
- As an employee I want to access the database and check records for Book
- As an employee I want to access the database and check records for Book and its Publisher, Genre, Author
- As an employee I want to access the database and add a Book and its Publisher, Genre, Author. If there isn’t a known Author, Publisher or Genre I want them to be blank
- As an employee, I want to be able to update the author, genre, or publisher record for a book.
- As an employee, I want to be able to delete either the author, genre, or publisher record on a book.
- As a novice software developer, I want to access the file structure and understand the backend logic easily.
- As a novice software developer, I want to understand the additional installations and interfaces.
- As a novice software developer, I want to use this RESTful API to test on a demo website page, using the different endpoints.


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



### Additional Features:

#### Testing
We created unit and integrations tests with hardcoded test data. The file testDataQuery.sql has the hardcoded test data for the our tests, but they can be changed inside the BookstoreApplicationTests.class and ControllerIntegrationTests.class

Basic unit tests of database in test/java/BookstoreApplicationTests.
We connected to the database through the repository classes and created Book, Author, Genre, and publisher objects to test against the database entries. 
![image](https://user-images.githubusercontent.com/7227339/114614012-9d549f80-9c69-11eb-976d-1765139d04ab.png)

Controller integration tests of the endpoints and CRUD methods in test/java/ControllerIntegrationTests.
We tested all of the GET methods in the API alongside the CRUD for the main table Book. We also tested post, put, and delete methods for the book endpoint

![image](https://user-images.githubusercontent.com/7227339/114614166-d12fc500-9c69-11eb-8484-bcc607f421df.png)


