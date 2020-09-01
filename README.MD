#ECards

### Technical Details

##### JEE Used Frameworks , libraries , ...

- Servlet , JSP , JSTL
- Spring Core  5.2.8
- Spring MVC  5.2.8
- Spring Data JPA
- Spring Security
- Log4J for logging
- others

### Project Structure

##### General Configuration
+ web.xml
+ MainDispatcherServlet-servlet.xml
+ org.ecards.configuration.core package
 	  + AppConfig - contains configuration for the localization bundles and loading for the other configuration  
 	  + DataSourceConfig for configuration of DB connectivity
 	  + JPAConfig  for hibernate configuration
 	  + RepositoryConfig for enabling the Spring Data JPA repositories


+ org.ecards.configuration.properties package
 	+ contain properties configuration files to be read by the above configuration 
	    to eliminate the hard coded configuration

##### Security Configuration
+ org.ecards.configuration.security package 
   +  configure the security paths , allowed roles , ....

##### DataBase Architecture - follows onion design pattern
+ Strickly achieve coherence by implemnting Onion Design pattern
   + Entities/Models 
   		+ org.ecards.entities package
   		+ org.ecards.entities.FKs / Embedable PKs
   		+ org.ecards.entities.validation / Custom Annotation Validations and Validators
   
   + Repositories - CRUD , Pageable 
   		+ org.ecards.repository package
 
   + Services 
   		+ org.ecards.service package


##### Controllers
###### MVC Controllers
+ MainController , UserController 
   + contain the View Business Logic which will call the service wrappers
   
###### Rest Controllers
+ Card Rest Controller  
   + contain action to be called as a rest api service by JQuery in the View

##### Views - JSP as the main view tech. of Spring MVC, JS ,CSS , bootstrap , JQuery , JSTL ,...
+ Login , Registeration , Index
  		+ contain hybrid between Controller action calls and Rest Api Call with javascript and JQuery to expose knowledge in both


##### Global Exception Handler
  		+ Special Kind of controller Advise as an implementation of AOP Programming


##### Resoucres - JS and 
  		+ contain Rest API Call , Validation for the UI to expose knowledge in both together

##### ScreenShots of the application 
 		+ included in screenshots folder

##### Run  
 		+ execute ECards.sql to create the DB on the  Schema configured in  org.ecards.configuration.core.DataSourceConfig.java using the Credential configured in the same file 
##### Login
 		+ End      Users : Login Screen is to register End Users only with ROLE_USER
 		+ Admin Users : There is no screen is to register Admin Users so use next Credential to access as admin with ROLE_ADMIN :  admin1/rootroot
 
 ##### Test
 		+ Chrome used to test the GUI Functionality and screenshot documentation
 		+ Postman used to test the Rest API
