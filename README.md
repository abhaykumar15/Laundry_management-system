# Laundry Management System

## Overview

The **Laundry Management System** is a Java-based web application developed using **Jakarta Servlets, JSP, JDBC, PostgreSQL, HTML, CSS, and Bootstrap**. The project is designed to automate and simplify the laundry booking and management process for students and employees inside a hostel or campus environment.

The system provides separate login modules for:

- Students
- Employees / Laundry Staff

Students can:

- Login securely
- Book laundry wash services
- Track laundry status
- Generate QR-based booking confirmations
- Access dashboards

Employees can:

- Login to employee portal
- View laundry requests
- Accept and process orders
- Manage laundry workflow

The project follows a layered architecture using:

```text
Controller → DAO → Database
```

and demonstrates concepts of:

- Java Web Development
- JDBC Connectivity
- Session Management
- Servlet-Based MVC Architecture
- PostgreSQL Database Integration
- QR Code Generation
- JSP Rendering

---

# Features

## Student Features

- Student login authentication
- Laundry booking system
- Dashboard access
- Booking status tracking
- Session-based authentication
- QR code generation for laundry orders

## Employee Features

- Employee login
- Laundry request management
- Accept laundry orders
- Track processing workflow

## System Features

- PostgreSQL database integration
- QR code generation using ZXing
- Session handling
- Dynamic JSP pages
- JDBC-based DAO architecture
- Servlet request handling

---

# Tech Stack

## Backend Technologies

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Jakarta Servlet | Request handling |
| JSP | Dynamic web pages |
| JDBC | Database connectivity |
| PostgreSQL | Database management |

---

## Frontend Technologies

| Technology | Purpose |
|---|---|
| HTML5 | Structure |
| CSS3 | Styling |
| Bootstrap | Responsive UI |
| JavaScript | Frontend interactions |

---

## Libraries Used

| Library | Purpose |
|---|---|
| PostgreSQL JDBC Driver | Database connection |
| ZXing | QR Code generation |
| Jackson | JSON handling |
| Google OAuth Libraries | API integrations |
| JSTL | JSP tag support |

---

## Development Tools

- Eclipse IDE
- Apache Tomcat Server
- PostgreSQL
- Git & GitHub

---

# Project Architecture

The project follows a simplified MVC-like layered architecture.

```text
Client Browser
      ↓
JSP Pages
      ↓
Servlet Controllers
      ↓
DAO Layer
      ↓
PostgreSQL Database
```

---

# Complete Project Structure

```text
Laundry_management-system-master/
│
└── Laundary_Management/
    │
    ├── .classpath
    ├── .project
    ├── .gitignore
    │
    ├── .settings/
    │
    └── src/
        │
        ├── main/
        │   │
        │   ├── java/
        │   │   │
        │   │   ├── Controller/
        │   │   │   ├── AcceptLaundryServlet.java
        │   │   │   ├── BookWashServlet.java
        │   │   │   ├── ChatBotServlet.java
        │   │   │   ├── DashboardServlet.java
        │   │   │   ├── EmployeeLoginServlet.java
        │   │   │   ├── LoginServlet.java
        │   │   │   └── StudentLogin.java
        │   │   │
        │   │   ├── DAO/
        │   │   │   ├── DBConnection.java
        │   │   │   ├── EmployeeDao.java
        │   │   │   ├── LaundaryDao.java
        │   │   │   └── StudentDao.java
        │   │   │
        │   │   └── model/
        │   │       ├── Employee.java
        │   │       ├── Hostel.java
        │   │       ├── LaundaryOrder.java
        │   │       └── Student.java
        │   │
        │   └── webapp/
        │       │
        │       ├── EmployeeLogin.jsp
        │       ├── StudentLogin.jsp
        │       ├── studentDashboard.jsp
        │       ├── employeeDashboard.jsp
        │       ├── index.jsp
        │       │
        │       ├── META-INF/
        │       └── WEB-INF/
        │           └── lib/
        │               ├── PostgreSQL JDBC jars
        │               ├── ZXing jars
        │               ├── Jackson jars
        │               └── Google OAuth jars
        │
        └── test/
```

---

# Detailed File Explanation

# Controller Layer

The controller layer handles HTTP requests and responses using Servlets.

Located inside:

```text
src/main/java/Controller/
```

---

## LoginServlet.java

Handles student authentication.

### Responsibilities

- Receives login form data
- Validates email and password
- Creates session after successful login
- Redirects users to dashboard
- Handles invalid login attempts

### Main Workflow

```text
Student Login Form
        ↓
LoginServlet
        ↓
StudentDao Validation
        ↓
Session Creation
        ↓
Dashboard Redirect
```

### Important Features

- Session timeout handling
- Input validation
- Authentication using database

---

## EmployeeLoginServlet.java

Handles employee login authentication.

### Responsibilities

- Employee credential validation
- Employee session management
- Employee dashboard redirection

---

## StudentLogin.java

Additional student login handling servlet.

### Responsibilities

- Student authentication flow
- Request processing

---

## DashboardServlet.java

Handles dashboard rendering.

### Responsibilities

- Dashboard access management
- Session-based access control
- Dashboard data preparation

---

## BookWashServlet.java

One of the core servlets of the project.

### Responsibilities

- Handles laundry booking requests
- Creates laundry orders
- Generates QR codes using ZXing
- Stores booking data in database
- Tracks booking status

### Laundry Booking Workflow

```text
Student Dashboard
        ↓
BookWashServlet
        ↓
Create Laundry Order
        ↓
Generate QR Code
        ↓
Save Order in PostgreSQL
        ↓
Booking Confirmation
```

### Features

- QR code generation
- Booking timestamps
- Laundry status management
- Session verification

---

## AcceptLaundryServlet.java

Handles acceptance of laundry orders.

### Responsibilities

- Employee accepts pending laundry orders
- Updates order status
- Tracks processing flow

---

## ChatBotServlet.java

Handles chatbot-related interactions.

### Responsibilities

- User assistance
- Query handling
- Basic automated support

---

# DAO Layer

Located inside:

```text
src/main/java/DAO/
```

The DAO layer directly communicates with PostgreSQL using JDBC.

---

## DBConnection.java

Handles PostgreSQL database connectivity.

### Database Configuration

```java
private static final String URL = "jdbc:postgresql://localhost:5432/laundarydb";
private static final String USER = "postgres";
private static final String PASS = "Abhay15";
```

### Responsibilities

- Load PostgreSQL JDBC Driver
- Establish database connection
- Return reusable JDBC connections

### Important Note

For security reasons, avoid pushing actual database passwords publicly.

Recommended approach:

```java
private static final String PASS = System.getenv("DB_PASSWORD");
```

---

## StudentDao.java

Handles student-related database operations.

### Responsibilities

- Student authentication
- Fetch student data
- Database queries for students

### Common Operations

- Login validation
- Student retrieval
- Student dashboard data

---

## EmployeeDao.java

Handles employee database operations.

### Responsibilities

- Employee authentication
- Employee data retrieval
- Laundry task management

---

## LaundaryDao.java

Main DAO for laundry operations.

### Responsibilities

- Create laundry bookings
- Update booking status
- Retrieve laundry orders
- Manage booking workflow

---

# Model Layer

Located inside:

```text
src/main/java/model/
```

Models represent entities and application data.

---

## Student.java

Represents student information.

### Possible Fields

- id
- name
- email
- hostel
- roomNumber
- password

---

## Employee.java

Represents employee information.

### Possible Fields

- employeeId
- name
- email
- password
- designation

---

## LaundaryOrder.java

Represents laundry booking orders.

### Fields

- orderId
- studentId
- serviceType
- bookingDate
- status
- qrGenerated

### Status Flow

```text
Pending → Accepted → Processing → Completed
```

---

## Hostel.java

Represents hostel-related information.

### Responsibilities

- Hostel data storage
- Student-hostel mapping

---

# Frontend Layer

Located inside:

```text
src/main/webapp/
```

---

## index.jsp

Main landing page.

### Features

- Navigation
- Login access
- Project homepage

---

## StudentLogin.jsp

Student authentication page.

### Features

- Email/password login
- Error message handling
- Form validation

---

## EmployeeLogin.jsp

Employee authentication page.

### Features

- Employee login form
- Authentication handling

---

## studentDashboard.jsp

Student dashboard interface.

### Features

- Laundry booking access
- Booking status tracking
- QR display
- Dashboard navigation

---

## employeeDashboard.jsp

Employee dashboard interface.

### Features

- Pending order management
- Laundry acceptance
- Order workflow tracking

---

# WEB-INF/lib Libraries

Located inside:

```text
WEB-INF/lib/
```

Contains all external JAR dependencies required by the application.

### Important Libraries

| Library | Purpose |
|---|---|
| postgresql.jar | PostgreSQL connectivity |
| core-3.5.1.jar | ZXing QR generation |
| jackson-core | JSON handling |
| google-oauth-client | OAuth support |

---

# Database Configuration

## Database Used

```text
PostgreSQL
```

## Database Name

```text
laundarydb
```

## Database Credentials

| Property | Value |
|---|---|
| Database | laundarydb |
| Username | postgres |
| Password | Abhay15 |
| Port | 5432 |

---

# Suggested Database Tables

The project likely uses tables such as:

```sql
students
employees
laundary_orders
hostels
```

---

# Application Workflow

# 1. Student Login Workflow

```text
StudentLogin.jsp
        ↓
LoginServlet
        ↓
StudentDao
        ↓
PostgreSQL Validation
        ↓
Session Creation
        ↓
studentDashboard.jsp
```

---

# 2. Laundry Booking Workflow

```text
Student Dashboard
        ↓
Book Wash Request
        ↓
BookWashServlet
        ↓
LaundaryDao
        ↓
Database Entry
        ↓
QR Code Generation
        ↓
Booking Confirmation
```

---

# 3. Employee Workflow

```text
Employee Login
        ↓
Employee Dashboard
        ↓
View Pending Orders
        ↓
AcceptLaundryServlet
        ↓
Update Order Status
```

---

# QR Code Workflow

The project uses the ZXing library for QR code generation.

### Workflow

```text
Laundry Booking
       ↓
Generate QR Data
       ↓
ZXing QRCodeWriter
       ↓
Convert to Image
       ↓
Store / Display QR
```

### Benefits

- Fast order identification
- Contactless verification
- Efficient laundry tracking

---

# Session Management

The application uses:

```text
HttpSession
```

### Session Features

- Login persistence
- User authentication tracking
- Session timeout handling
- Secure dashboard access

Example:

```java
session.setMaxInactiveInterval(30 * 60);
```

This sets:

```text
30 minutes session timeout
```

---

# How to Run the Project

# Prerequisites

Install:

- Java JDK 17 or above
- Eclipse IDE
- Apache Tomcat 10+
- PostgreSQL

---

# Step 1: Clone Repository

```bash
git clone https://github.com/yourusername/laundry-management-system.git
```

---

# Step 2: Import Project into Eclipse

```text
File → Import → Existing Projects into Workspace
```

Select:

```text
Laundary_Management
```

---

# Step 3: Configure PostgreSQL

Create database:

```sql
CREATE DATABASE laundarydb;
```

---

# Step 4: Update Database Credentials

Open:

```text
src/main/java/DAO/DBConnection.java
```

Modify:

```java
private static final String USER = "postgres";
private static final String PASS = "your_password";
```

---

# Step 5: Configure Tomcat

1. Add Apache Tomcat server in Eclipse
2. Right click project
3. Run on Server

---

# Application URL

```text
http://localhost:8080/Laundary_Management/
```

---

# Key Concepts Implemented

## JDBC Connectivity

Used for:

- Database operations
- CRUD functionality
- Authentication queries

---

## Session Handling

Used for:

- Login persistence
- Secure dashboards
- User tracking

---

## Servlet-Based MVC

Used for:

- Request handling
- Business flow management
- Dynamic page rendering

---

## QR Code Generation

Implemented using:

```text
ZXing Library
```

---

# Security Improvements Recommended

Current project stores plaintext passwords.

Recommended improvements:

- BCrypt password hashing
- HTTPS deployment
- CSRF protection
- Prepared statements everywhere
- JWT authentication
- Role-based authorization

---

# Future Enhancements

Possible improvements:

- Online payment gateway
- SMS notifications
- Email notifications
- Laundry pickup scheduling
- Admin dashboard
- REST API integration
- Mobile app integration
- Cloud deployment
- AI chatbot improvements
- Real-time order tracking

---

# Learning Outcomes

This project demonstrates understanding of:

- Java Servlets
- JSP Development
- JDBC Connectivity
- PostgreSQL Integration
- MVC Architecture
- Session Management
- QR Code Generation
- DAO Design Pattern
- Full Stack Java Development

---

# Screenshots Section

You can add screenshots here.

Example:

```md
## Login Page
![Login](screenshots/login.png)

## Student Dashboard
![Dashboard](screenshots/dashboard.png)
```

---

# GitHub Repository Best Practices

Recommended files:

```text
README.md
.gitignore
screenshots/
SQL schema file
```

---

# Recommended .gitignore

```gitignore
bin/
target/
.classpath
.project
.settings/
*.log
*.tmp
*.jar
```

---

# Author

## Abhay Chandra

MCA Student | Java Full Stack Developer

---

# License

This project is developed for educational and learning purposes.

---

# Conclusion

The Laundry Management System is a complete Java web application that digitizes and automates hostel laundry operations. The project integrates authentication, booking workflows, database connectivity, QR code generation, and session management into a practical real-world solution using Java EE technologies.

