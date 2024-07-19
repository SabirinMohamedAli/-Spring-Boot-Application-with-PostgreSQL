# -Spring-Boot-Application-with-PostgreSQL
# Personal Expense Tracker

## Project Description
The Personal Expense Tracker is a web application designed to help users manage their daily expenses efficiently. It provides features such as expense tracking, budget planning, and generating financial reports to help users make informed financial decisions.

## Features
- **Expense Tracking**: Record and categorize daily expenses.
- **Budget Planning**: Plan and monitor your budget effectively.
- **Financial Reports**: Generate detailed reports on your spending patterns.

## Technologies Used
- **Java**
- **Spring Boot**
- **PostgreSQL**
- **Thymeleaf**
- **Bootstrap**
- **JPA (Java Persistence API)**
- **Spring Security**

## Setup and Installation
1. **Clone the Repository**:
    ```sh
    git clone https://github.com/SabirinMohamedAli/-Spring-Boot-Application-with-PostgreSQL.git
    ```
2. **Navigate to the Project Directory**:
    ```sh
    cd Personal_Expense_Tracker
    ```
3. **Update Database Configuration**:
    - Open `src/main/resources/application.properties` and update the database configurations (URL, username, password) according to your PostgreSQL setup.
    ```properties
   spring.application.name=Personal_Expense_Tracker
# Server Configuration
server.port = 3006
#Data configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

4. **Run the Application**:
   main application
   
   

## Usage
1. Open your web browser and navigate to `http://localhost:3006`.
2. Register or log in to start tracking your expenses.
3. Use the navigation menu to access different features such as adding expenses, viewing reports, and managing your budget.

## Project Structure
- `src/main/java/com/example/Personal_Expense_Tracker`: Contains the main application files.
- `src/main/resources/templates`: Contains the Thymeleaf templates.
- `src/main/resources/application.properties`: Configuration file for the application.

## Error Handling
- Proper error handling has been implemented to manage exceptions and errors gracefully.
- The application uses try-catch blocks, logging, and provides user-friendly error messages.

## Contributions
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

## Contact
If you have any questions or suggestions, feel free to contact me at [thaprinmohamett1333@gmail.com](mailto:thaprinmohamett1333@gmail.com).
