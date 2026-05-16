# LIBSM
A full-featured Library Management System built with Java and SQL (MySQL/PostgreSQL). This application helps librarians manage books, members, borrowing records, and fines efficiently.
## Features
### Book Management

    Add, update, delete, and search books

    Track book availability (available/borrowed/lost)

    Search by title, author, ISBN, or genre

    View book details and borrowing history

### Member Management

    Register new members

    Update member information

    View member borrowing history

    Track membership status (active/suspended)

### Borrowing & Returns

    Borrow books (with due date calculation)

    Return books and calculate fines

    Automatic availability updates

    Borrowing history tracking

### Fine Management

    Automatic fine calculation for overdue books

    Fine payment recording

    View outstanding fines per member

### Reports & Dashboard

    List currently borrowed books

    Overdue books report

    Most popular books

    Member activity summary

    Revenue from fines

### Authentication

    Admin login system

    Role-based access control

    Secure password storage

## Tech Stack
Component	Technology

Language	Java (JDK 20)

Database	MySQL 

JDBC Driver	MySQL Connector 

Build Tool	ANT

## Project Structure
library-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/library/
│   │   │   ├── model/          # Entity classes (Book, Member, Transaction)
│   │   │   ├── dao/            # Database access objects
│   │   │   ├── service/        # Business logic layer
│   │   │   ├── ui/             # User interface (Swing/JavaFX)
│   │   │   ├── utils/          # Helper classes
│   │   │   └── config/         # Database configuration
│   │   └── resources/
│   │       ├── db/             # SQL scripts
│   │       └── application.properties
│   └── test/                   # Unit tests
├── lib/                         # External JARs (if not using Maven)
├── database/
│   └── schema.sql              # Database schema
├── pom.xml                     # Maven configuration
└── README.md
