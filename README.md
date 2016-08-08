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
### GET User
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
    
---
### GET All User Information
---

Returns the information for all users in the datastore.

* **URL**

  `/findUser/`

* **Method:**
  
  `GET`
  
*  **URL Params**

   **Required:**
 
     None

   **Optional:**
 
     None

* **Data Params**

  **Required:**
    
    None

  **Optional:**
    
    None

---
### POST Create User
---

Create a new user with the specified information.

* **URL**

`/createUser/`

* **Method:**

`POST`

*  **URL Params**

**Required:**

 None

**Optional:**

 None

* **Data Params**

**Required:**

JSON Data Object

**Optional:**

None

---
### POST Update User
---

Create an existing user data with the specified information.

* **URL**

`/updateUser/:id`

* **Method:**

`POST`

*  **URL Params**

**Required:**

 `*id*`

**Optional:**

 None

* **Data Params**

**Required:**

JSON Data Object

**Optional:**

None

