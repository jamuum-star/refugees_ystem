# Project Summary: Refugee Management System
# Overview:
The refugee system project aims to provide a comprehensive
platform for managing refugee data and operations. It leverages Spring Boot 
for backend development, integrating with Thymeleaf for server-side rendering 
and PDF generation for exporting refugee information.

# Key Features:
## 1.  Registration and Management:
* Allows registration of refugees with fields such 
as full name, family size, contact details, address,
and status (Refugee, Asylum Seeker, Returner).
* Supports CRUD operations (Create, Read, Update, Delete) for managing refugee records.

## 2. Authentication and Authorization:
* Includes user authentication using Spring Security 
to control access to system functionalities based on 
user roles.

## 3. Search and Listing:
* Provides search functionality to filter refugees
based on keywords, facilitating easy retrieval
and management of refugee records.
* Displays a list of refugees with detailed information 
for administrators or authorized users.

## 4. Export to PDF:
* Implements functionality to export refugee data into PDF format for offline access or printing purposes.
* Utilizes the iText library for PDF generation, allowing customization of PDF layout and content.

## 5. Error Handling and Exception Management:
* Implements global exception handling to manage errors and provide appropriate responses to users, enhancing system reliability.

## 6. User Interface
* Uses Thymeleaf templates for rendering dynamic web pages, ensuring a responsive and user-friendly interface.
* Incorporates Bootstrap for styling and responsive design, improving overall user experience.

## Technologies Used:
* Backend: Spring Boot, Spring Security, Spring Data JPA
* Frontend: Thymeleaf, Bootstrap
* Database: PostgreSQL (assumed based on previous contexts)
* Docker file
* PDF Generation: iText library
* Error Handling: Custom exception handling with Spring MVC

## Future Enhancements:
* Integration with additional APIs or services for enhanced refugee support.
* Implementing internationalization (i18n) for multi-language support.
* Enhancing security features and access control mechanisms.

# Steps to Run Your Spring Boot Project:
* extract zip folder open with Java Intellij
* Build the Project: dependences with Maven on pom.xml file press right click press maven reload project
* run docker file command on the teriminal docker-compose up -d and go to browser localhost:5055
* enter mail: salim@gmail.com password: root 
* create the postgres server username: root password: root
* run the  main project RefugeeApplication 
* on the browser localhost:2000 
* login page enter username: salim password: 123 press login