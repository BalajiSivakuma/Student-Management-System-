import java.util.*;

public class StudentManagementSystem {
    static List<Student> students = new ArrayList<>();
    static Set<Integer> studentIds = new HashSet<>();
    static Map<Integer, String> grades = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Display All Students (Sorted by Name)");
            System.out.println("5. Display All Students (Sorted by ID)");
            System.out.println("6. Add/Update Grade");
            System.out.println("7. Display Grade for a Student");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    removeStudent(scanner);
                    break;
                case 3:
                    searchStudentById(scanner);
                    break;
                case 4:
                    displayStudentsSortedByName();
                    break;
                case 5:
                    displayStudentsSortedById();
                    break;
                case 6:
                    addOrUpdateGrade(scanner);
                    break;
                case 7:
                    displayGrade(scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    static void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();

        scanner.nextLine();

        if (studentIds.contains(id)) {
            System.out.println("Error: Student ID already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student(id, name, age);
        students.add(student);
        studentIds.add(id);

        System.out.println("Student added successfully!");
    }

    static void removeStudent(Scanner scanner) {
        System.out.print("Enter Student ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = students.removeIf(student -> student.getId() == id);

        if (removed) {
            studentIds.remove(id);
            grades.remove(id);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Error: Student ID not found!");
        }
    }

    static void searchStudentById(Scanner scanner) {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void displayStudentsSortedByName() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        students.sort(Comparator.comparing(Student::getName));
        for (Student student : students) {
            System.out.println(student);
        }
    }

    static void displayStudentsSortedById() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        students.sort(Comparator.comparingInt(Student::getId));
        for (Student student : students) {
            System.out.println(student);
        }
    }

    static void addOrUpdateGrade(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!studentIds.contains(id)) {
            System.out.println("Error: Student ID not found!");
            return;
        }

        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();

        grades.put(id, grade);
        System.out.println("Grade added/updated successfully!");
    }

    static void displayGrade(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!studentIds.contains(id)) {
            System.out.println("Error: Student ID not found!");
            return;
        }

        String grade = grades.get(id);
        if (grade != null) {
            System.out.println("Grade for Student ID " + id + ": " + grade);
        } else {
            System.out.println("No grade available.");
        }
    }
}
