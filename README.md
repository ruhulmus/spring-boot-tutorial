# springboot-jwt-tutorial
Spring boot Security + JWT Demo Example

Here I configured Spring Security for JWT. 

Here JWT performing 2 operations:
1. Generate JWT Token - We Expose a POST API on /authenticate Endpoint. And Passing correct username and password to generate a JSON Web Token(JWT)
2. Validate JWT Token - If user tries to access GET API on /testuri Endpoint And passing The generated Token in header as Authorization Bearer token. So It will allow access only if request has a valid JSON Web Token(JWT)