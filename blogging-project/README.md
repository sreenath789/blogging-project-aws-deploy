<h1 align = "center"> BLOGGING PROJECT </h1>

<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.0.6-brightgreen.svg" />
</a>

<a >
    <img alt="MySQL" src="https://img.shields.io/badge/MySQL-blue.svg">
</a>
</p>

This project is a basic web application that allows users to sign in, sign up, and manage their profile information. Additionally, users can create posts and view posts created by other users. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features of the application.

---
<br>

## Framework Used
* Spring Boot

---
<br>

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation
* Swagger

<br>

## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:
```
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/<DatabaseName>
spring.datasource.username = <userName>
spring.datasource.password = <password>
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

```
<br>

## Language Used
* Java

---
<br>

## Data Model

The Job data model is defined in the Job class, which has the following attributes:
<br>

* User Model
```
Id : integer
firstName : string
lastName : string
blogName : string
blogBio : string
email : string
password : string
mobileNumber : string
```

* Post Model
```
postId = Integer
postContent : String
postCaption : string
postLocation : string
postCreatedTime : LocalDateTime
postType : enum
@ManyToOne
user : User

```

* Follow Model
```
followId : Integer
@ManyToOne
user : currentUser
@ManyToOne
user : currentUserFollower
```
* Comment Model
```
commentId : Integer
commentBody : string
commentCreationTime : LocalDateTime
@ManyToOne
user : commentUser
@ManyToOne
post : instapost
```
* Authentication Token
```
tokenId : Integer
token : string
tokenCreationDate : LocalDate
@OneToOne 
user : User
```
## Data Flow

1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.
4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

---

<br>


## API End Points

The following endpoints are available in the API:

* User Controller:
```
POST /user/signup: create a new user account
POST /user/signin: authenticate a user and receive an authentication token
DELETE /user/signout: authenticate a user and delete authentication token
POST /post : create a new post
DELETE /post : delete the post
PUT /post : update the post
GET /posts : get posts by postLocation
POST /comment : add comment on the post
DELETE /comment : delete the comment 
POST /follow : follow the user
DELETE /unfollow : unfollow the user
```



<br>

## DataBase Used
* SQL database
```
We have used Persistent database to implement CRUD Operations.
```
---
<br>

## Project Summary

The project is a basic web application built using Java and the Spring framework. It allows users to sign up, sign in, and manage their profile information. Users can also create and view posts. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features. The API endpoints include user signup, signin, and update details, post creation and retrieval, and authentication token creation.



## Author

👤 **Sreenath Golla**

* GitHub: [Sreenath Golla](https://github.com/sreenath789/blogging-project/tree/main/blogging-project)

---

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page]("url").
    
---

## Show your support

Give a ⭐️ if this project helped you!
    
---

## 📝 License



This project is [MIT]("url") licensed.
    
---
