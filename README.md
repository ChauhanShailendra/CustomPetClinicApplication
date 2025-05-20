# CustomPetClinicApplication
This is a custom clinic application an extension to the spring boot's popular pet clinic app

**Once the mvn clean install is successfully run.**

Start the application using
 a) mvn spring-boot:run -Dspring-boot.run.profiles=h2 **(if wanted to use h2 db)**
 b) Before, running the above mysql version command, connect to mysql console and source the user.sql, schema.sql and data.sql files using:
     (i) source <path-to-user-sql-file>/user.sql
     (ii) source <path-to-user-sql-file>/schema.sql
     (iii) source <path-to-user-sql-file>/data.sql
   **Now, start the server using command,** mvn spring-boot:run -Dspring-boot.run.profiles=mysql
    


**This application covers**
 a) Two endpoints:
   (i) Adding the Pet Object along with the pet attributes (like temperament, length, weight) and pet type details.
         api url - http://localhost:9090/pet-details/addPetInfo
         Method type - POST
   (ii) Fetching the pet details
         api url - http://localhost:9090/pet-details/by-pet/14
         Method type - GET

 b) Changes in the SQL schema.
    (i) Added 'pet_details' table in the schema.
    (ii) Added 'price' column in 'visits' table.

 c) Includes Models and DTo's
 d) CRUD operation for Owner, Pet, PetDetails, Visits and Exception handling
 e) Includes, solution to the follow up questions:
    (i) Pluggable petAgeFormatter (yearOnlyFormatter and detailedFormatter)
    (ii) Sending notification via Email or SMS (emailNotifier or smsNotifier) when a visit is scheduled.
    (iii) Plugabble pricing (flatPricing, timeBasedPricing and locationBasedPricing).
    (iv) Swappable backends (in-memory(h2) and mysql db).

 f) Converting the response of the api (http://localhost:9090/pet-details/addPetInfo) in HATEOAS format.


**Additionally, adding the postman collection along with the api payload and api response for the reference.**
 
    

 

