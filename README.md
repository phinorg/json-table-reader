# JSON Table Reader

A simple Java project that reads JSON data using Jackson databind and displays it as a formatted table.

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Project Structure

```
json-table-reader/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           ├── Employee.java
        │           ├── EmployeeData.java
        │           └── JsonTableReader.java
        └── resources/
            └── data.json
```

## Building the Project

```bash
mvn clean compile
```

## Running the Project

### Option 1: Using Maven Exec Plugin
```bash
mvn exec:java
```

### Option 2: Using Custom JSON File
```bash
mvn compile exec:java -Dexec.args="path/to/your/file.json"
```

### Option 3: After Building JAR
```bash
mvn package
java -cp target/json-table-reader-1.0-SNAPSHOT.jar:target/dependency/* com.example.JsonTableReader
```

## Example Output

The program will display a formatted table with employee data:

```
--------------------------------------------------------------------------------------------
| ID   | Name          | Department   | Salary      | Email                      |
--------------------------------------------------------------------------------------------
| 1    | John Doe      | Engineering  | $95,000.00  | john.doe@example.com       |
| 2    | Jane Smith    | Marketing    | $75,000.00  | jane.smith@example.com     |
| 3    | Bob Johnson   | Sales        | $65,000.00  | bob.johnson@example.com    |
| 4    | Alice Williams| Engineering  | $105,000.00 | alice.williams@example.com |
| 5    | Charlie Brown | HR           | $70,000.00  | charlie.brown@example.com  |
--------------------------------------------------------------------------------------------

Summary:
Total employees: 5
Total salary: $410,000.00
Average salary: $82,000.00
```

## JSON Format

The JSON file should have the following structure:

```json
{
  "employees": [
    {
      "id": 1,
      "name": "John Doe",
      "department": "Engineering",
      "salary": 95000,
      "email": "john.doe@example.com"
    }
  ]
}
```

## Dependencies

- Jackson Core 2.15.2
- Jackson Databind 2.15.2


