# One To Many Using N-Tier Architecture With Spring Boot 
## To Try Project
- Clone the project(Fork optional)You need to install postgresql and also need to create a table named "authorBookOneToMany" and run project and enter this url to
your browser "http://localhost:8080/swagger-ui/index.html"

## About Project
- Unidirectional one to many project.N-tier architecture has used 
> :warning: **Be very careful here!***: Do not use cascadetype.All on many side it will cause the deletion of parent also.
Remember none of the operations on relations cascade by manual, the control is up to you

![image](https://user-images.githubusercontent.com/96066271/209255161-963994fe-f9ae-45b0-a910-0f374b8d7836.png)


