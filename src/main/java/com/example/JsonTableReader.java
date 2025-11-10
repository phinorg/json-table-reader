package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class JsonTableReader {
    private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public static void main(String[] args) {
        JsonTableReader reader = new JsonTableReader();
        
        // Try to read from command line argument, otherwise use default resource file
        String jsonFilePath = args.length > 0 ? args[0] : null;
        
        try {
            EmployeeData data = reader.readJsonFile(jsonFilePath);
            reader.displayTable(data.getEmployees());
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public EmployeeData readJsonFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeData data;

        if (filePath != null && !filePath.isEmpty()) {
            // Read from file path
            File file = new File(filePath);
            data = objectMapper.readValue(file, EmployeeData.class);
            System.out.println("Reading from file: " + filePath);
        } else {
            // Read from resource file
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.json");
            if (inputStream == null) {
                throw new IOException("Resource file 'data.json' not found");
            }
            data = objectMapper.readValue(inputStream, EmployeeData.class);
            System.out.println("Reading from resource file: data.json");
        }

        return data;
    }

    public void displayTable(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        // Calculate column widths
        int idWidth = Math.max(4, String.valueOf(employees.size()).length() + 2);
        int nameWidth = Math.max(10, employees.stream()
                .mapToInt(e -> e.getName() != null ? e.getName().length() : 0)
                .max().orElse(10) + 2);
        int deptWidth = Math.max(12, employees.stream()
                .mapToInt(e -> e.getDepartment() != null ? e.getDepartment().length() : 0)
                .max().orElse(12) + 2);
        int salaryWidth = 12;
        int emailWidth = Math.max(25, employees.stream()
                .mapToInt(e -> e.getEmail() != null ? e.getEmail().length() : 0)
                .max().orElse(25) + 2);

        // Print header
        printSeparator(idWidth + nameWidth + deptWidth + salaryWidth + emailWidth + 4);
        System.out.printf("| %-" + (idWidth - 1) + "s | %-" + (nameWidth - 1) + "s | %-" + 
                         (deptWidth - 1) + "s | %-" + (salaryWidth - 1) + "s | %-" + 
                         (emailWidth - 1) + "s |%n", 
                         "ID", "Name", "Department", "Salary", "Email");
        printSeparator(idWidth + nameWidth + deptWidth + salaryWidth + emailWidth + 4);

        // Print data rows
        for (Employee employee : employees) {
            String formattedSalary = currencyFormatter.format(employee.getSalary());
            System.out.printf("| %-" + (idWidth - 1) + "d | %-" + (nameWidth - 1) + "s | %-" + 
                             (deptWidth - 1) + "s | %-" + (salaryWidth - 1) + "s | %-" + 
                             (emailWidth - 1) + "s |%n",
                             employee.getId(),
                             employee.getName() != null ? employee.getName() : "",
                             employee.getDepartment() != null ? employee.getDepartment() : "",
                             formattedSalary,
                             employee.getEmail() != null ? employee.getEmail() : "");
        }

        // Print footer
        printSeparator(idWidth + nameWidth + deptWidth + salaryWidth + emailWidth + 4);
        
        // Print summary
        System.out.println("\nSummary:");
        System.out.println("Total employees: " + employees.size());
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        double avgSalary = totalSalary / employees.size();
        System.out.println("Total salary: " + currencyFormatter.format(totalSalary));
        System.out.println("Average salary: " + currencyFormatter.format(avgSalary));
    }

    private void printSeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}


