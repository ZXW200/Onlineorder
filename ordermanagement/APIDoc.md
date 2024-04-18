### RESTful API Documentation: 



## User Management

#### Base URL
```
/api
```

#### API Endpoints

1. **Create User Account**
    - **Method:** `POST`
    - **URL:** `/createUser`
    - **Description:** Creates a new user account.
    - **Request Body:** `{ "username": "string", "password": "string", "role": "string" }` (Note: `id` and `addtime` are generated automatically.)
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `"User created successfully"`

2. **User Login**
    - **Method:** `POST`
    - **URL:** `/login`
    - **Description:** Authenticates a user.
    - **Request Body:** `{ "username": "string", "password": "string" }`
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `"Logged in successfully"` (if credentials are valid)
      - **Status Code:** 400 Bad Request
      - **Body:** `"Invalid credentials"` (if credentials are invalid)

#### Response Status Codes
- **200 OK**: The request was successful.
- **400 Bad Request**: The request was invalid or cannot be served. This includes invalid credentials.
- **500 Internal Server Error**: An error occurred on the server side.

#### Sample Request and Response

1. **Sample Request:** Create User Account
    - **Request:** `POST /api/createUser`
      ```
      Body: {
        "username": "johndoe",
        "password": "password123",
        "role": "user"
      }
      ```
    - **Response:** 
      ```
      Status: 200 OK
      Body: "User created successfully"
      ```

2. **Sample Request:** User Login
    - **Request:** `POST /api/login`
      ```
      Body: {
        "username": "johndoe",
        "password": "password123"
      }
      ```
    - **Response (if credentials are valid):** 
      ```
      Status: 200 OK
      Body: "Logged in successfully"
      ```

    - **Response (if credentials are invalid):** 
      ```
      Status: 400 Bad Request
      Body: "Invalid credentials"
      ```





### Table Management

#### Base URL
```
/api/tables
```

#### API Endpoints

1. **Get All Tables**
    - **Method:** `GET`
    - **URL:** `/`
    - **Description:** Retrieves a list of all tables.
    - **Request Parameters:** None
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `[ { id, number, seatingCapacity, status }, ... ]`

2. **Get Table by ID**
    - **Method:** `GET`
    - **URL:** `/{id}`
    - **Description:** Retrieves details of a specific table by ID.
    - **Path Variables:** `id` - ID of the table.
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `{ id, number, seatingCapacity, status }`

3. **Create a New Table**
    - **Method:** `POST`
    - **URL:** `/`
    - **Description:** Creates a new table record.
    - **Request Body:** `{ number, seatingCapacity, status }`
    - **Response:**
      - **Status Code:** 201 Created
      - **Body:** `{ id, number, seatingCapacity, status }`

4. **Update a Table**
    - **Method:** `PUT`
    - **URL:** `/{id}`
    - **Description:** Updates an existing table record.
    - **Path Variables:** `id` - ID of the table to update.
    - **Request Body:** `{ number, seatingCapacity, status }`
    - **Response:**
      - **Status Code:** 200 OK
      - **Body:** None (or confirmation message)

5. **Delete a Table**
    - **Method:** `DELETE`
    - **URL:** `/{id}`
    - **Description:** Deletes a table record.
    - **Path Variables:** `id` - ID of the table to delete.
    - **Response:**
      - **Status Code:** 200 OK
      - **Body:** None (or confirmation message)

#### Response Status Codes
- **200 OK**: The request was successful.
- **201 Created**: A new resource was successfully created.
- **400 Bad Request**: The request was invalid or cannot be served.
- **404 Not Found**: The requested resource was not found.
- **500 Internal Server Error**: An error occurred on the server side.

#### Sample Request and Response

1. **Sample Request:** Get Table by ID
   
    - **Request:** `GET /api/tables/1`
    - **Response:** 
      ```
      Status: 200 OK
      Body: {
        "id": 1,
        "number": 10,
        "seatingCapacity": 4,
        "status": "AVAILABLE"
      }
      ```
    
2. **Sample Request:** Create a New Table
    - **Request:** `POST /api/tables`
      
      ```
      Body: {
        "number": 15,
        "seatingCapacity": 6,
        "status": "AVAILABLE"
      }
      ```
    - **Response:** 
      
      ```
      Status: 201 Created
      Body: {
        "id": 5,
        "number": 15,
        "seatingCapacity": 6,
        "status": "AVAILABLE"
      }
      ```



---

### Order Management

#### Base URL
```
/api/orders
```

#### API Endpoints

1. **Get All Orders**
    - **Method:** `GET`
    - **URL:** `/`
    - **Description:** Retrieves a list of all orders.
    - **Request Parameters:** None
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `[ { id, userId, tableId, orderTime, status }, ... ]`

2. **Get Order by ID**
    - **Method:** `GET`
    - **URL:** `/{id}`
    - **Description:** Retrieves details of a specific order by ID.
    - **Path Variables:** `id` - ID of the order.
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `{ id, userId, tableId, orderTime, status }`
      - **Status Code:** 404 Not Found
      - **Body:** None (if no order is found with the given ID)

3. **Create a New Order**
    - **Method:** `POST`
    - **URL:** `/`
    - **Description:** Creates a new order record.
    - **Request Body:** `{ userId, tableId, orderTime, status }`
    - **Response:**
      - **Status Code:** 201 Created
      - **Body:** `{ id, userId, tableId, orderTime, status }`

4. **Update an Order**
    - **Method:** `PUT`
    - **URL:** `/{id}`
    - **Description:** Updates an existing order record.
    - **Path Variables:** `id` - ID of the order to update.
    - **Request Body:** `{ userId, tableId, orderTime, status }`
    - **Response:**
      - **Status Code:** 200 OK
      - **Body:** None (or confirmation message)
      - **Status Code:** 404 Not Found
      - **Body:** None (if no order is found with the given ID)

5. **Delete an Order**
    - **Method:** `DELETE`
    - **URL:** `/{id}`
    - **Description:** Deletes an order record.
    - **Path Variables:** `id` - ID of the order to delete.
    - **Response:**
      - **Status Code:** 200 OK
      - **Body:** None (or confirmation message)
      - **Status Code:** 404 Not Found
      - **Body:** None (if no order is found with the given ID)

#### Response Status Codes
- **200 OK**: The request was successful.
- **201 Created**: A new resource was successfully created.
- **404 Not Found**: The requested resource was not found.
- **500 Internal Server Error**: An error occurred on the server side.

#### Sample Request and Response

1. **Sample Request:** Get Order by ID
    - **Request:** `GET /api/orders/1`
    - **Response:** 
      ```
      Status: 200 OK
      Body: {
        "id": 1,
        "userId": 123,
        "tableId": 10,
        "orderTime": "2021-01-01T12:00:00",
        "status": "PENDING"
      }
      ```

2. **Sample Request:** Create a New Order
    - **Request:** `POST /api/orders`
      ```
      Body: {
        "userId": 123,
        "tableId": 10,
        "orderTime": "2021-01-01T12:00:00",
        "status": "PENDING"
      }
      ```
    - **Response:** 
      ```
      Status: 201 Created
      Body: {
        "id": 2,
        "userId": 123,
        "tableId": 10,
        "orderTime": "2021-01-01T12:00:00",
        "status": "PENDING"
      }
      ```

---



### Reservation Management

#### Base URL
```
/api/reservations
```

#### Overview
This API manages reservations, allowing for the creation, retrieval, updating, and deletion of reservation records.

#### API Endpoints

1. **Get All Reservations**
    - **Method:** `GET`
    - **URL:** `/`
    - **Description:** Retrieves a list of all reservations.
    - **Request Parameters:** None
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `[ { id, userId, tableId, reservationTime, status }, ... ]`

2. **Get Reservation by ID**
    - **Method:** `GET`
    - **URL:** `/{id}`
    - **Description:** Retrieves details of a specific reservation by ID.
    - **Path Variables:** `id` - ID of the reservation.
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `{ id, userId, tableId, reservationTime, status }`
      - **Status Code:** 404 Not Found (if no reservation is found with the given ID)

3. **Create a New Reservation**
    - **Method:** `POST`
    - **URL:** `/`
    - **Description:** Creates a new reservation record.
    - **Request Body:** `{ userId, tableId, reservationTime, status }`
    - **Response:**
      - **Status Code:** 201 Created
      - **Body:** `{ id, userId, tableId, reservationTime, status }`

4. **Update a Reservation**
    - **Method:** `PUT`
    - **URL:** `/{id}`
    - **Description:** Updates an existing reservation record.
    - **Path Variables:** `id` - ID of the reservation to update.
    - **Request Body:** `{ userId, tableId, reservationTime, status }`
    - **Response:**
      - **Status Code:** 200 OK
      - **Body:** None (or confirmation message)

5. **Delete a Reservation**
    - **Method:** `DELETE`
    - **URL:** `/{id}`
    - **Description:** Deletes a reservation record.
    - **Path Variables:** `id` - ID of the reservation to delete.
    - **Response:**
      - **Status Code:** 200 OK
      - **Body:** None (or confirmation message)

#### Response Status Codes
- **200 OK**: The request was successfully processed.
- **201 Created**: A new resource was successfully created.
- **404 Not Found**: The requested resource was not found.
- **500 Internal Server Error**: An error occurred on the server side.

#### Sample Request and Response

1. **Sample Request:** Get Reservation by ID
    - **Request:** `GET /api/reservations/1`
    - **Response:** 
      ```
      Status: 200 OK
      Body: {
        "id": 1,
        "userId": 123,
        "tableId": 10,
        "reservationTime": "2021-01-01T12:00:00",
        "status": "CONFIRMED"
      }
      ```

2. **Sample Request:** Create a New Reservation
    - **Request:** `POST /api/reservations`
      ```
      Body: {
        "userId": 123,
        "tableId": 11,
        "reservationTime": "2021-01-02T18:00:00",
        "status": "PENDING"
      }
      ```
    - **Response:** 
      ```
      Status: 201 Created
      Body: {
        "id": 2,
        "userId": 123,
        "tableId": 11,
        "reservationTime": "2021-01-02T18:00:00",
        "status": "PENDING"
      }
      ```

---

### Order Detail Management

#### Base URL
```
/api/orderDetails
```

#### Overview
This API manages order details, allowing for the creation, retrieval, updating, and deletion of order detail records.

#### API Endpoints

1. **Get All Order Details**
    - **Method:** `GET`
    - **URL:** `/`
    - **Description:** Retrieves a list of all order details.
    - **Request Parameters:** None
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `[ { id, orderId, menuItemId, quantity, price }, ... ]`

2. **Get Order Detail by ID**
    - **Method:** `GET`
    - **URL:** `/{id}`
    - **Description:** Retrieves details of a specific order detail by ID.
    - **Path Variables:** `id` - ID of the order detail.
    - **Response:** 
      - **Status Code:** 200 OK
      - **Body:** `{ id, orderId, menuItemId, quantity, price }`

3. **Create a New Order Detail**
    - **Method:** `POST`
    - **URL:** `/`
    - **Description:** Creates a new order detail record.
    - **Request Body:** `{ orderId, menuItemId, quantity, price }`
    - **Response:**
      - **Status Code:** 201 Created
      - **Body:** `{ id, orderId, menuItemId, quantity, price }`

4. **Update an Order Detail**
    - **Method:** `PUT`
    - **URL:** `/{id}`
    - **Description:** Updates an existing order detail record.
    - **Path Variables:** `id` - ID of the order detail to update.
    - **Request Body:** `{ orderId, menuItemId, quantity, price }`
    - **Response:**
      - **Status Code:** 200 OK
      - **Body:** None (or confirmation message)

5. **Delete an Order Detail**
    - **Method:** `DELETE`
    - **URL:** `/{id}`
    - **Description:** Deletes an order detail record.
    - **Path Variables:** `id` - ID of the order detail to delete.
    - **Response:**
      - **Status Code:** 200 OK
      - **Body:** None (or confirmation message)

#### Response Status Codes
- **200 OK**: The request was successfully processed.
- **201 Created**: A new resource was successfully created.
- **404 Not Found**: The requested resource was not found.
- **500 Internal Server Error**: An error occurred on the server side.

#### Sample Request and Response

1. **Sample Request:** Get Order Detail by ID
    - **Request:** `GET /api/orderDetails/1`
    - **Response:** 
      ```
      Status: 200 OK
      Body: {
        "id": 1,
        "orderId": 101,
        "menuItemId": 501,
        "quantity": 2,
        "price": 19.99
      }
      ```

2. **Sample Request:** Create a New Order Detail
    - **Request:** `POST /api/orderDetails`
      ```
      Body: {
        "orderId": 102,
        "menuItemId": 502,
        "quantity": 3,
        "price": 29.99
      }
      ```
    - **Response:** 
      ```
      Status: 201 Created
      Body: {
        "id": 2,
        "orderId": 102,
        "menuItemId": 502,
        "quantity": 3,
        "price": 29.99
      }
      ```

---

Certainly! Below is a basic API documentation for your `MenuItemController` in the Spring Boot application. This documentation details the endpoints, HTTP methods, request and response formats, and the purpose of each API call.

---

### Menu Item Management

#### Base URL
`/api/menuItems`

#### 1. Get All Menu Items
- **Endpoint**: `GET /`
- **Description**: Retrieves a list of all menu items.
- **Response**: 
  - `200 OK` - Success, returns a list of `MenuItem` objects.
  - `ContentType`: `application/json`

#### 2. Get Menu Item By ID
- **Endpoint**: `GET /{id}`
- **Path Variable**: `id` - The ID of the menu item to retrieve.
- **Description**: Retrieves a single menu item by its ID.
- **Response**:
  - `200 OK` - Success, returns the `MenuItem` object.
  - `404 Not Found` - If no menu item exists with the given ID.
  - `ContentType`: `application/json`

#### 3. Create a New Menu Item
- **Endpoint**: `POST /`
- **Request Body**: A `MenuItem` object to be created.
  - Example: `{"name": "Pizza", "description": "Delicious cheese pizza", "price": 9.99, "category": "Main", "available": true}`
- **Description**: Creates a new menu item.
- **Response**:
  - `201 Created` - Success, returns the created `MenuItem` object.
  - `ContentType`: `application/json`

#### 4. Update an Existing Menu Item
- **Endpoint**: `PUT /{id}`
- **Path Variable**: `id` - The ID of the menu item to update.
- **Request Body**: A `MenuItem` object with updated fields.
  - Example: `{"name": "Updated Pizza", "description": "Delicious cheese and pepperoni pizza", "price": 10.99, "category": "Main", "available": true}`
- **Description**: Updates an existing menu item.
- **Response**:
  - `200 OK` - Success, returns the updated `MenuItem` object.
  - `404 Not Found` - If no menu item exists with the given ID.
  - `ContentType`: `application/json`

#### 5. Delete a Menu Item
- **Endpoint**: `DELETE /{id}`
- **Path Variable**: `id` - The ID of the menu item to delete.
- **Description**: Deletes a menu item.
- **Response**:
  - `200 OK` - Success, menu item deleted.
  - `404 Not Found` - If no menu item exists with the given ID.

---

### Notes
- All `GET` endpoints do not require a request body.
- The `POST` and `PUT` endpoints expect a `MenuItem` object in JSON format in the request body.
- Replace `{id}` with the actual ID of the menu item in the URL path.
- This documentation assumes standard HTTP status codes for simplicity. Additional error handling can be implemented as required.

---

