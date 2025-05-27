# Resturant Booking System
**Group 2**    
**Author: Emelia Jarleback, Daniel Gran and Jessica Olofsson**

A console-based booking system for restaurants, developed as a group project for educational purposes. The system manages reservations, customers, and tables, and provides a menu where users can create, update, delete, and view bookings.

## Features

- Create, update, and delete reservations
- Add customers and tables
- Automatically populate test data at startup
- User menu for easy navigation
- Exception handling 
- Console interface with improved output formatting

## Technologies and Frameworks

- **Java 17**
- **Spring Framework**
- **JPA (Hibernate)**
- **Maven** (for project structure and dependencies)

- **JUnit** 


## Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── se/grupp2/
│   │       ├── domain/         # Domain-classes: Customer, Table, Reservation
│   │       ├── dao/            # DAO interfaces and implementations (ex. BookingDao BookingDaoJpaImpl)
│   │       ├── service/        # Service classes: BookingService, etc.
│   │       └── main/           # Client-class with menu handling
│   └── resources/              # Resources (e.g., application.xml)
└── test/                       # Unit tests
```
## Test Data

When the program runs, the database is automatically populated with test data (customers, tables, reservations) using a ``` setup() ``` method. This makes it easy to test the system without manually entering data each time.

##  Key Design Decisions
- The service layer (BookingService) handles business logic.
- The DAO pattern is used to separate database logic from business logic.
- Domain classes are annotated with JPA annotations for persistence.
- Menu handling is implemented using switch-case in the main() method.
- Custom exception classes, such as TableNotAvailableException, are used to handle specific scenarios.

## Workflow
The project was developed using a combination of individual and mob programming. We used Git and GitHub for version control. Merge conflicts and code reviews were handled collaboratively within the team.

## How to Run the Project
1. Clone the repository:
```
git clone https://github.com/Jessica-a11y/grupp2.git
cd grupp2
```
2. Make the run.sh executable: 
```
chmod u+x run.sh
```
3. Build and Run the project with the following script: 
```
./run.sh 
```
**Note**: Make sure Java and Maven are installed on your machine.
