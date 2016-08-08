<img src = "https://egen.solutions/images/egen-logo.png" align = "right">

# egen-be-challenge
A RESTful Web Service for User Management using Java Spark and MongoDB


Requirements
=======

* MongoDB
* Java Spark
* Maven
* Java JDK 1.8
* Junit

Instructions
=======

* Install MongoDB and Maven
* Download the entire project source code
* Install all the dependencies
* Start the mongoDB server
* Run the appController.java 
* Use the GUI at localhost:8080/index.html or follow the API documentation below


API Documentation
=======

The following is the details on all API route implementations.

---
### GET Catalog
---

Returns the information for the specified user.

* **URL**

  `/findUser/:id`

* **Method:**
  
  `GET`
  
*  **URL Params**

   **Required:**
 
     `*id*`

   **Optional:**
 
     None

* **Data Params**

  **Required:**
    
    None

  **Optional:**
    
    None
