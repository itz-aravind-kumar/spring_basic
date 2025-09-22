Perfect 👍 Here’s the **full beautiful README in Markdown** (with badges, emojis, and everything) that you can directly copy-paste into `README.md`:

# 🚀 Learn1 Spring Boot JWT Authentication API  

![Java](https://img.shields.io/badge/Java-21-orange?logo=java&logoColor=white)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=springboot&logoColor=white)  
![Spring Security](https://img.shields.io/badge/Spring%20Security-6+-darkgreen?logo=springsecurity&logoColor=white)  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql&logoColor=white)  
![Maven](https://img.shields.io/badge/Maven-Build-red?logo=apachemaven&logoColor=white)  
![JWT](https://img.shields.io/badge/JWT-Authentication-purple?logo=jsonwebtokens&logoColor=white)  

A **Spring Boot REST API** with **JWT-based authentication** and secure user management.  
It follows **modern Spring Security best practices** and uses **PostgreSQL** for persistence.  

---

## ✨ Features  
- 🔐 User **registration** & **login**  
- 🔑 **JWT token** generation & validation  
- 👤 Secure endpoint: `/users/me` (fetch current user details)  
- 🔒 **Password hashing** (BCrypt)  
- 🛡️ Role-based **user model**  
- ⚙️ Modern **`SecurityFilterChain`** configuration  
- 📦 **DTOs** for safe user data exposure  

---

## 🛠️ Tech Stack  
- ☕ **Java 21**  
- 🍃 **Spring Boot 3.5.6**  
- 🔐 **Spring Security 6+**  
- 🗄️ **JPA/Hibernate**  
- 🐘 **PostgreSQL**  
- 📜 **jjwt** (JWT library)  
- 📦 **Maven**  

---

## 📂 Project Structure  
```plaintext
src/
 └── main/
     ├── java/com/learn/Learn1/
     │   ├── controllers/      # REST controllers
     │   ├── models/           # JPA entities
     │   ├── repositories/     # Spring Data JPA repositories
     │   ├── security/         # JWT filter & security config
     │   └── services/         # Business logic & JWT service
     └── resources/
         └── application.properties   # DB & JWT config
````

---

## 🔑 How Authentication Works

1. **📝 Registration** → User registers via `/auth/register`.

   * Password is **hashed** & saved in DB.

2. **🔐 Login** → User logs in via `/auth/login`.

   * On success, a **JWT token** is generated & returned.

3. **🛡️ JWT Filter** → For protected endpoints:

   * Extracts JWT from `Authorization` header.
   * Validates token & loads user.
   * Sets user into **Spring Security Context**.

4. **👤 Current User Endpoint** → `/users/me`

   * Returns logged-in user’s `id`, `name`, and `email` via **DTO**.

---

## ⚙️ Key Files

### 📄 `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/learn1
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

jwt.secret=your_very_long_32+_char_secret_key
```

### 👤 `User.java` (Entity)

Represents a user with fields:

* `id` (UUID)
* `name`
* `email`
* `password` (hashed)
* `role`

### 📦 `UserRepository.java`

Extends `JpaRepository`.
Custom method:

```java
Optional<User> findByEmail(String email);
```

### ⚙️ `UserService.java`

* Handles **user registration** & **lookup**
* Uses **BCrypt** for password hashing

### 🔑 `JwtService.java`

* Generates & validates JWT tokens
* Stores **email as subject**
* Uses secure key (>= 32 chars for HS256)

### 🛡️ `JwtAuthFilter.java`

* Extracts JWT from request
* Validates & sets authentication
* Logs details for debugging

### 🌐 `UserController.java`

* `/users/me` → returns logged-in user as DTO
* Returns **404** if not found

---

## 📡 Example API Usage

### 📝 Register

```http
POST /auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "yourpassword"
}
```

### 🔐 Login

```http
POST /auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "yourpassword"
}
```

✅ Response:

```json
{ "token": "your_jwt_token_here" }
```

### 👤 Get Current User

```http
GET /users/me
Authorization: Bearer your_jwt_token
```

✅ Response:

```json
{
  "id": "uuid-here",
  "name": "John Doe",
  "email": "john@example.com"
}
```

---

## 🔒 Security Notes

* Passwords are **always hashed** before saving.
* JWT secret key must be **>= 32 chars** for HS256.
* Only safe user info is exposed via **DTOs**.
* Debug logging in `JwtAuthFilter` helps troubleshoot.

---

## ▶️ Running the Project

1. Setup **PostgreSQL** and create database `learn1`
2. Update `application.properties` with DB credentials & JWT secret
3. Build & run:

```bash
./mvnw clean package
./mvnw spring-boot:run
```

4. Test APIs using **Postman / curl**

---

## 🛠️ Troubleshooting

* **❌ `/users/me` → "User not found"**

  * Check JWT subject matches user in DB
  * Ensure user registered & logged in

* **❌ WeakKeyException**

  * Use a **32+ char** JWT secret

---

## 🚀 Future Enhancements

* 🔑 Role-based access control
* 🔄 Refresh tokens
* 📧 Email verification & password reset

---

## 📬 Contact

For help, questions, or contributions, feel free to reach out ✨


---

👉 Do you also want me to add a **sequence diagram (PlantUML)** in the README that shows the JWT authentication flow (Register → Login → JWT Filter → Controller)? That would make it look even more professional.

