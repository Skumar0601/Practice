package com.cts;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class Main {

	    public static void main(String[] args) {
	        
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myJpaUnit");  // Specify your persistence unit name
	        EntityManager em = emf.createEntityManager();

	        DepartmentDAO departmentDAO = new DepartmentDAO(em);
	        EmployeeDAO employeeDAO = new EmployeeDAO(em);

	        Scanner scanner = new Scanner(System.in);
	        boolean exit = false;

	        while (!exit) {
	            // Main Menu
	            System.out.println("\nMain Menu:");
	            System.out.println("1. Manage Departments");
	            System.out.println("2. Manage Employees");
	            System.out.println("3. Exit");

	            try {
	                int choice = scanner.nextInt();
	                scanner.nextLine();  // Consume newline character

	                switch (choice) {
	                    case 1:
	                        manageDepartments(scanner, departmentDAO);
	                        break;
	                    case 2:
	                        manageEmployees(scanner, employeeDAO);
	                        break;
	                    case 3:
	                        exit = true;
	                        break;
	                    default:
	                        System.out.println("Invalid choice, please try again.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input, please enter a valid option.");
	                scanner.nextLine();  // Consume invalid input
	            }
	        }

	        // Close resources
	        scanner.close();
	        em.close();
	        emf.close();
	    }

	    // Manage Department Operations
	    private static void manageDepartments(Scanner scanner, DepartmentDAO departmentDAO) {
	        boolean exit = false;
	        while (!exit) {
	            System.out.println("\nDepartment Management Menu:");
	            System.out.println("1. Create Department with Employees");
	            System.out.println("2. View Department");
	            System.out.println("3. Update Department");
	            System.out.println("4. Delete Department");
	            System.out.println("5. Back to Main Menu");

	            try {
	                int choice = scanner.nextInt();
	                scanner.nextLine();  // Consume newline

	                switch (choice) {
	                    case 1:
	                        createDepartmentWithEmployees(scanner, departmentDAO);
	                        break;
	                    case 2:
	                        viewDepartment(scanner, departmentDAO);
	                        break;
	                    case 3:
	                        updateDepartment(scanner, departmentDAO);
	                        break;
	                    case 4:
	                        deleteDepartment(scanner, departmentDAO);
	                        break;
	                    case 5:
	                        exit = true;
	                        break;
	                    default:
	                        System.out.println("Invalid choice, please try again.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input, please enter a valid option.");
	                scanner.nextLine();  // Consume invalid input
	            }
	        }
	    }

	    // Manage Employee Operations
	    private static void manageEmployees(Scanner scanner, EmployeeDAO employeeDAO) {
	        boolean exit = false;
	        while (!exit) {
	            System.out.println("\nEmployee Management Menu:");
	            System.out.println("1. Create Employee");
	            System.out.println("2. View Employee");
	            System.out.println("3. Update Employee");
	            System.out.println("4. Delete Employee");
	            System.out.println("5. Back to Main Menu");

	            try {
	                int choice = scanner.nextInt();
	                scanner.nextLine();  // Consume newline

	                switch (choice) {
	                    case 1:
	                        createEmployee(scanner, employeeDAO);
	                        break;
	                    case 2:
	                        viewEmployee(scanner, employeeDAO);
	                        break;
	                    case 3:
	                        updateEmployee(scanner, employeeDAO);
	                        break;
	                    case 4:
	                        deleteEmployee(scanner, employeeDAO);
	                        break;
	                    case 5:
	                        exit = true;
	                        break;
	                    default:
	                        System.out.println("Invalid choice, please try again.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input, please enter a valid option.");
	                scanner.nextLine();  // Consume invalid input
	            }
	        }
	    }

	    // Create a Department with Employees
	    private static void createDepartmentWithEmployees(Scanner scanner, DepartmentDAO departmentDAO) {
	        System.out.print("Enter department name: ");
	        String departmentName = scanner.nextLine();
	        Department department = new Department(departmentName);

	        Set<Employee> employees = new HashSet<>();

	        System.out.print("Enter number of employees to add: ");
	        int numEmployees = scanner.nextInt();
	        scanner.nextLine();  // Consume newline

	        for (int i = 0; i < numEmployees; i++) {
	            System.out.print("Enter first name of employee " + (i + 1) + ": ");
	            String firstName = scanner.nextLine();
	            System.out.print("Enter last name of employee " + (i + 1) + ": ");
	            String lastName = scanner.nextLine();
	            System.out.print("Enter salary of employee " + (i + 1) + ": ");
	            double salary = scanner.nextDouble();
	            scanner.nextLine();  // Consume newline

	            Employee employee = new Employee(firstName, lastName, salary);
	            employee.setDepartment(department);
	            employees.add(employee);
	        }

	        departmentDAO.createDepartmentWithEmployees(department, employees);
	    }

	    // View a Department
	    private static void viewDepartment(Scanner scanner, DepartmentDAO departmentDAO) {
	        System.out.print("Enter department ID to view: ");
	        Long departmentId = scanner.nextLong();
	        scanner.nextLine();  // Consume newline

	        Department department = departmentDAO.findDepartment(departmentId);
	        if (department != null) {
	            System.out.println("Department: " + department.getName());
	            for (Employee employee : department.getEmployees()) {
	                System.out.println("Employee: " + employee.getFirstName() + " " + employee.getLastName() +
	                        " - Salary: " + employee.getSalary());
	            }
	        } else {
	            System.out.println("Department not found.");
	        }
	    }

	    // Update a Department
	    private static void updateDepartment(Scanner scanner, DepartmentDAO departmentDAO) {
	        System.out.print("Enter department ID to update: ");
	        Long departmentId = scanner.nextLong();
	        scanner.nextLine();  // Consume newline

	        Department department = departmentDAO.findDepartment(departmentId);
	        if (department != null) {
	            System.out.print("Enter new department name: ");
	            String newName = scanner.nextLine();
	            department.setName(newName);
	            departmentDAO.updateDepartment(department);
	        } else {
	            System.out.println("Department not found.");
	        }
	    }

	    // Delete a Department
	    private static void deleteDepartment(Scanner scanner, DepartmentDAO departmentDAO) {
	        System.out.print("Enter department ID to delete: ");
	        Long departmentId = scanner.nextLong();
	        scanner.nextLine();  // Consume newline

	        departmentDAO.deleteDepartment(departmentId);
	    }

	    // Create an Employee
	    private static void createEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
	        System.out.print("Enter first name: ");
	        String firstName = scanner.nextLine();
	        System.out.print("Enter last name: ");
	        String lastName = scanner.nextLine();
	        System.out.print("Enter salary: ");
	        double salary = scanner.nextDouble();
	        scanner.nextLine();  // Consume newline

	        Employee employee = new Employee(firstName, lastName, salary);
	        employeeDAO.createEmployee(employee);
	    }

	    // View an Employee
	    private static void viewEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
	        System.out.print("Enter employee ID to view: ");
	        Long employeeId = scanner.nextLong();
	        scanner.nextLine();  // Consume newline

	        Employee employee = employeeDAO.findEmployee(employeeId);
	        if (employee != null) {
	            System.out.println("Employee: " + employee.getFirstName() + " " + employee.getLastName() +
	                    " - Salary: " + employee.getSalary());
	        } else {
	            System.out.println("Employee not found.");
	        }
	    }

	    // Update an Employee
	    private static void updateEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
	        System.out.print("Enter employee ID to update: ");
	        Long employeeId = scanner.nextLong();
	        scanner.nextLine();  // Consume newline

	        Employee employee = employeeDAO.findEmployee(employeeId);
	        if (employee != null) {
	            System.out.print("Enter new first name: ");
	            String newFirstName = scanner.nextLine();
	            employee.setFirstName(newFirstName);

	            System.out.print("Enter new last name: ");
	            String newLastName = scanner.nextLine();
	            employee.setLastName(newLastName);

	            System.out.print("Enter new salary: ");
	            double newSalary = scanner.nextDouble();
	            scanner.nextLine();  // Consume newline
	            employee.setSalary(newSalary);

	            employeeDAO.updateEmployee(employee);
	        } else {
	            System.out.println("Employee not found.");
	        }
	    }

	    // Delete an Employee
	    private static void deleteEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
	        System.out.print("Enter employee ID to delete: ");
	        Long employeeId = scanner.nextLong();
	        scanner.nextLine();  // Consume newline

	        employeeDAO.deleteEmployee(employeeId);
	    }
	}