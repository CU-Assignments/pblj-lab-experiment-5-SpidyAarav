Java program that serializes and deserializes a Student object. It saves the Student object to a file and then reads it back, displaying the student details.
The program handles exceptions like FileNotFoundException, IOException, and ClassNotFoundException.

Steps:
1. Create a Student class with id, name, and GPA.
2. Serialize the Student object: Convert the object to a byte stream and save it to a file.
3. Deserialize the Student object: Read the byte stream from the file and convert it back into an object.
4. Exception handling: Handle possible exceptions such as FileNotFoundException, IOException, and ClassNotFoundException.


----Implementation
---Student Class: The Student class implements the Serializable interface, allowing it to be serialized. It has three fields: id, name, and gpa.
---serializeStudent(): This method serializes a Student object to a file using ObjectOutputStream. The object is written to a file named student.ser.
---deserializeStudent(): This method deserializes the Student object from the file using ObjectInputStream. If successful, it returns the deserialized Student object.
---Exception Handling: The program handles FileNotFoundException, IOException, and ClassNotFoundException during the serialization and deserialization processes.



Test Cases:

Test Case 1: Serialize and Deserialize a valid student object.
  
Input: Student(1, "John Doe", 3.75)
Expected Output:
Student object has been serialized and saved to file.
Student object has been deserialized.
Deserialized Student Details:
Student ID: 1, Name: John Doe, GPA: 3.75
  
Test Case 2: Try to deserialize from a non-existent file.
Expected Output:
Error: File not found.
  
Test Case 3: Handle invalid class during deserialization.
Input: Manually modify the class file to simulate a ClassNotFoundException.
Expected Output:
Error: Class not found.

  CODE:
  import java.io.*;
class MyString implements Serializable {
 private static final long serialVersionUID = 1L;
 private String value;
 public MyString(String value) {
 this.value = value;
 }
 public String getValue() {
 return value;
 }
}
class Student implements Serializable {
 private static final long serialVersionUID = 1L;
 int id;
 MyString name;
 double gpa;
 public Student(int id, MyString name, double gpa) {
 this.id = id;
 this.name = name;
 this.gpa = gpa;
 }
 public void display() {
 System.out.println("ID: " + id + ", Name: " + name.getValue() + ", GPA: " + gpa);
 }
}
public class StudentSerialization {
 public static void main(String[] args) {
 String filename = "student.ser";
 try (ObjectOutputStream oos = new ObjectOutputStream(new
FileOutputStream(filename))) {
 Student student = new Student(101, new MyString("Alice"), 3.8);
 oos.writeObject(student);
 System.out.println("Student object serialized successfully.");
 } catch (IOException e) {
 e.printStackTrace();
 }
 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
 Student student = (Student) ois.readObject();
 System.out.println("Deserialized Student:");
 student.display();
 } catch (IOException | ClassNotFoundException e) {
 e.printStackTrace();
 }
 }
}
