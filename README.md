# Chegg Interview JWT Spring Boot

## Build Instruction
1. Setup MySQL

Create database
```sql
CREATE DATABASE 'my_test_db';
```
Create user table
```sql
create table user
(
	id bigint auto_increment primary key,
	username varchar(20) not null,
	email varchar(50) not null,
	password varchar(120) not null
);

create unique index user_email_uindex
	on user (email);

create unique index user_id_uindex
	on user (id);

create unique index user_username_uindex
	on user (username);
```

2. Maven build
Go to project root folder and maven build:
```
mvn clean package
```

3. Dockerize the Application
Go to project root folder that contains docker-compose.yml and run the following cmd:
```
docker-compose build
```
Start the docker container spring-jwt-server and spring-jwt-mysqldb
```
docker-compose up
```

## Usage Instruction
### APIs

User sign up:

| POST | /api/user/auth |
| --- | ----------- |
| Request Body | {"username": String - username, "email": String - email address, "password": String - user password} |
| Response | 200 - User registered! |

User login:

| POST | /api/user/auth |
| --- | ----------- |
| Request Body | {"username": String - username, "password": String - user password} |
| Response | 200 - {{"token": String - jwt token,"type":"Bearer","id": Long - user id,"username": String - username,"email": String - user email,"roles":[]} |

Get user by id:

| GET | /api/user/info |
| --- | ----------- |
| Request Header | Authorization = "Bearer" + jwt token |
| Request Header | id = String - user id |
| Response | {"id": Long - user id, "username": String - username,"email":"haojun@gmail.com"} |

Get user by email:

| GET | /api/user/info |
| --- | ----------- |
| Request Header | Authorization = "Bearer" + jwt token |
| Request Header | email = String - user email address |
| Response | {"id": Long - user id, "username": String - username,"email":"haojun@gmail.com"} |


### Code Structure:
#### security: Spring Security Integration configuration and Security Objects:
* WebSecurityConfig extends Spring-security:: WebSecurityConfigurerAdapter
* UserDetailsServiceImpl implements Spring-security:: UserDetailsService
* UserDetailsImpl implements Spring-security:: UserDetails
* AuthEntryPointJwt implements Spring-security:: AuthenticationEntryPoint
* AuthTokenFilter extends Spring-web:: OncePerRequestFilter
* JwtService provides methods for generating, parsing, validating JWT

#### controller: front-end APIs
* UserAuthController - implement user signup and login
* UserInfoController - implement get user by id and email address

#### repository: extends Spring JPA for database queries.
* UserRepository extends JpaRepository<User, Long>

#### model: database model
* User - user model

#### payload: request and response objects


### resources/application.properties
The configuration properties for database and jwt token.

## More Implementation
1. Add role information
2. Add token expiration and log out.
3. Add login retry limit
4. Add email address validation
5. Add restriction on password.

## Assumptions
1. username and email is unique
2. email address is also correct
3. no restriction on password format