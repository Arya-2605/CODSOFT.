import java.io.*;
import java.util.*;

/* Student Class */
class Student implements Serializable {
    int rollNo;
    String name;
    String grade;

    Student(int rollNo, String name, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNo + " | " + name + " | " + grade;
    }
}

/* Main Management System */
public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> removeStudent();
                case 4 -> searchStudent();
                case 5 -> displayStudents();
                case 6 -> {
                    saveToFile();
                    System.out.println("Exiting... Data Saved.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }

    /* Add Student */
    static void addStudent() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();

        if (findStudent(roll) != null) {
            System.out.println("Student already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        if (name.isEmpty() || grade.isEmpty()) {
            System.out.println("Fields cannot be empty!");
            return;
        }

        students.add(new Student(roll, name, grade));
        System.out.println("Student Added Successfully!");
    }

    /* Edit Student */
    static void editStudent() {
        System.out.print("Enter Roll No to Edit: ");
        int roll = sc.nextInt();
        sc.nextLine();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter New Name: ");
        s.name = sc.nextLine();

        System.out.print("Enter New Grade: ");
        s.grade = sc.nextLine();

        System.out.println("Student Updated!");
    }

    /* Remove Student */
    static void removeStudent() {
        System.out.print("Enter Roll No to Remove: ");
        int roll = sc.nextInt();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        students.remove(s);
        System.out.println("Student Removed!");
    }

    /* Search Student */
    static void searchStudent() {
        System.out.print("Enter Roll No to Search: ");
        int roll = sc.nextInt();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found!");
        } else {
            System.out.println("Roll | Name | Grade");
            System.out.println(s);
        }
    }

    /* Display Students */
    static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("\nRoll | Name | Grade");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /* Find Student */
    static Student findStudent(int roll) {
        for (Student s : students) {
            if (s.rollNo == roll)
                return s;
        }
        return null;
    }

    /* Save to File */
    static void saveToFile() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    /* Load from File */
    static void loadFromFile() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}
