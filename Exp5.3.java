Menu-based Java application that allows you to add employee details, display all employees, and exit. The employee details will be stored in a file, and the program will
read the file to display the stored employee information.

Steps:
1. Create an Employee class with fields like name, id, designation, and salary.
2. Create a menu with three options:
Add an Employee
Display All Employees
Exit
3. Store Employee Data in a File: Serialize the employee objects and store them in a file.
4. Read Employee Data from the File: Deserialize the employee objects from the file and display the details.
5. Handle Exceptions: Handle file I/O exceptions.



---Implementation
  
Employee Class: This class contains details like name, id, designation, and salary. It implements Serializable to allow serialization of Employee objects.
addEmployee(): This method takes input from the user for an employee's details, creates an Employee object, and saves it to a file using ObjectOutputStream.
saveEmployeeToFile(): This method appends employee details to a file. The file is opened in append mode (true parameter in FileOutputStream).
displayAllEmployees(): This method reads all employee objects from the file and prints their details.
readEmployeesFromFile(): This method reads the employee objects from the file using ObjectInputStream and stores them in a list. 
The loop continues until the end of the file is reached (IOFException).




Test Cases:

Test Case 1: Add a new employee and display all employees.
Steps: Select option 1 to add a new employee, then select option 2 to display all employees.
Input:
Employee Name: John Doe
Employee ID: 101
Designation: Software Engineer
Salary: 50000
  
Expected Output:
Employee added successfully!
Employee ID: 101, Name: John Doe, Designation: Software Engineer, Salary: 50000.0

Test Case 2: Try adding multiple employees and display all of them.
Steps: Add multiple employees (using option 1) and then display all employees (using option 2).
Expected Output:
Employee added successfully!
Employee ID: 101, Name: John Doe, Designation: Software Engineer, Salary: 50000.0
Employee added successfully!
Employee ID: 102, Name: Jane Smith, Designation: Manager, Salary: 75000.0


  CODE:
import java.io.*;
import java.util.*;
class MyDouble implements Serializable {
 private static final long serialVersionUID = 1L;
 private double value;
 public MyDouble(double value) {
 this.value = value;
 }
 public double getValue() {
 return value;
 }
}
class Employee implements Serializable {
 private static final long serialVersionUID = 1L;
 int id;
 String name, designation;
 MyDouble salary;
 public Employee(int id, String name, String designation, MyDouble salary) {
 this.id = id;
 this.name = name;
 this.designation = designation;
 this.salary = salary;
 }
 public void display() {
 System.out.println("ID: " + id + ", Name: " + name + ", Designation: " +
designation + ", Salary: " + salary.getValue());
 }
}
public class EmployeeManagement {
 private static final String FILE_NAME = "employees.ser";
   private static List<Employee> employeeList = new ArrayList<>();
 public static void addEmployee() {
 Scanner sc = new Scanner(System.in);
 try {
 System.out.print("Enter ID: ");
 int id = sc.nextInt();
 sc.nextLine();
 System.out.print("Enter Name: ");
 String name = sc.nextLine();
 System.out.print("Enter Designation: ");
 String designation = sc.nextLine();
 System.out.print("Enter Salary: ");
 double salary = sc.nextDouble();
 Employee emp = new Employee(id, name, designation, new
MyDouble(salary));
 employeeList.add(emp);
 saveToFile();
 System.out.println("Employee added successfully!");
 } catch (InputMismatchException e) {
 System.out.println("Invalid input! Please enter the correct data type.");
 sc.nextLine();
 }
 }
 public static void displayEmployees() {
 loadFromFile();
 if (employeeList.isEmpty()) {
 System.out.println("No employees found.");
 } else {
 System.out.println("Employee Details:");
 for (Employee emp : employeeList) {
 emp.display();
 }
 }
 }
 private static void saveToFile() {
 try (ObjectOutputStream oos = new ObjectOutputStream(new
FileOutputStream(FILE_NAME))) {
 oos.writeObject(employeeList);
 } catch (IOException e) {
 System.out.println("Error saving employee data.");
 }
 }
 private static void loadFromFile() {
 try (ObjectInputStream ois = new ObjectInputStream(new
FileInputStream(FILE_NAME))) {
   employeeList = (List<Employee>) ois.readObject();
 } catch (IOException | ClassNotFoundException e) {
 employeeList = new ArrayList<>();
 }
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 while (true) {
 System.out.println("\n1. Add Employee\n2. Display All\n3. Exit");
 System.out.print("Choose an option: ");
 int choice = sc.nextInt();
 switch (choice) {
 case 1:
 addEmployee();
 break;
 case 2:
 displayEmployees();
 break;
 case 3:
 System.out.println("Exiting application.");
 return;
 default:
 System.out.println("Invalid choice! Try again.");
 }
 }
 }
}
