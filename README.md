# CustomPetClinicApplication

This is a custom clinic application — an extension of Spring Boot's popular Pet Clinic app.

---

## 🛠️ Build & Run

After successfully running:


### ▶️ Start the application using:

- **H2 Database:**
``` mvn spring-boot:run -Dspring-boot.run.profiles=h2 ```

- **MySQL Database:**
1. Connect to MySQL console
2. Run the following in order:
   ```
   source <path-to-user-sql-file>/user.sql
   source <path-to-user-sql-file>/schema.sql
   source <path-to-user-sql-file>/data.sql
   ```
3. Start the server:
   ```
   mvn spring-boot:run -Dspring-boot.run.profiles=mysql
   ```

---

## 🚀 Features Covered

### 🧩 API Endpoints

- **Add Pet Object with extended attributes**  
- Method: `POST`  
- URL: `http://localhost:9090/pet-details/addPetInfo`

- **Fetch Pet Details by Pet ID**  
- Method: `GET`  
- URL: `http://localhost:9090/pet-details/by-pet/14`

---

### 🛠️ Database Enhancements

- Added a new table: `pet_details`
- Added a new column: `price` in the `visits` table

---

### 📦 Code Features

- Models and DTOs for clean data handling
- Full CRUD operations for:
- Owner
- Pet
- PetDetails
- Visits
- Exception handling

---

### 💡 Pluggable Components

- **Pet Age Formatter**
- `yearOnlyFormatter`
- `detailedFormatter`

- **Notification on Visit Scheduling**
- `emailNotifier`
- `smsNotifier`

- **Pricing Strategies**
- `flatPricing`
- `timeBasedPricing`
- `locationBasedPricing`

- **Swappable Data Storage Backends**
- In-memory (H2)
- MySQL

---

### 🔗 HATEOAS Support

- Converts response of `addPetInfo` API into HATEOAS-compliant format.

---

## 📬 Postman Collection

Includes:
- API payloads
- Sample responses

_For reference and quick testing._
