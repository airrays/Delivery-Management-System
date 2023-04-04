# AmenityReservationSystem

This app was created with Bootify.io - tips on working with the code [can be found here](https://bootify.io/next-steps/). Feel free to contact us for further questions.

## Development

During development it is recommended to use the profile `local`. In IntelliJ, `-Dspring.profiles.active=local` can be added in the VM options of the Run Configuration after enabling this property in "Modify options".

Update your local database connection in `application.yml` or create your own `application-local.yml` file to override settings for development.

Lombok must be supported by your IDE. For this, in IntelliJ install the Lombok plugin and enable annotation processing - [learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

The application can then be started with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/amenity-reservation-system-0.0.1-SNAPSHOT.jar
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  

---
### Features for the APP
- Users should be able to login
- We will assume that the accounts of residents are pre-created and there will be no sign-up feature.
- Users should be able to view their reservations.
- Users should be able to create new reservations by selecting the amenity type, data, and time.
- **Only logged-in users** should be able to see the reservations page and create reservations.
- We should check the capacity and only create new reservations if the current number of reservations does not exceed the capacity.
### Technologies
- Bootify
- Hibernate
- JPA
- Swagger
- H2 In-memory Database
- Thymeleaf
- Spring security

---
repos folder contains the code for the data access layer, repositories. I am using JPA methods to retrieve data.
They are pre-made query methods I can use by defining them inside the repository interface.
Just defining them, not actully contents

Notice that our repository classes extend the JpaRepository interface. This is the interface that allows us to use the mentioned methods.