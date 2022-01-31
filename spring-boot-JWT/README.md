# Spring boot jwt tutorial
## Spring boot Security + JWT Demo Example

Here I have Created a maven project and configured **JSON WEB TOKEN(JWT)** with Spring Boot.

### JWT Will Perform below 2 operations: 
**1. Generate JWT Token -** We Expose a POST API on /authenticate Endpoint. And Passing correct username and password to generate a JSON Web Token(JWT).

**2. Validate JWT Token -** If user tries to access GET API on /testuri Endpoint and passing The generated Token in header as Authorization Bearer token. So It will allow access only if request has a valid JSON Web Token(JWT)


#### JWT Token Generate with Valid credentials :

Make a POST request with url http://localhost:8089/authenticate.
Body should have valid username and password. Here I used username : "ruhul" and password : "password".  Please make sure that body should be in JSON format.
If username and password is valid then it will return a token and success Response with 200 Status code.
*Example is below :*
Generate JWT Token ![view](https://github.com/ruhulmus/spring-boot-tutorial/blob/main/spring-boot-JWT/assets/Generate-JWT-Token.png)

#### Validate JWT Token & Make Rest Api call :

Try to access the url http://localhost:8089/testuri using the above generated token in the header as Bearer Token.
If the Authorization Bearer token is valid it will be return a valid response.
*Example is below:*
Authenticate JWT Token ![view](https://github.com/ruhulmus/spring-boot-tutorial/blob/main/spring-boot-JWT/assets/Authenticate-JWT-token.png)
