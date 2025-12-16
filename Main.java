import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management System (JDBC) =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Delete Course");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Invalid input!");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Student ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter DOB (YYYY-MM-DD): ");
                    String dob = sc.nextLine();

                    Student student = new Student(name, id, dob);

                    System.out.print("How many courses? ");
                    int n = Integer.parseInt(sc.nextLine());

                    for (int i = 0; i < n; i++) {
                        System.out.print("Course Name: ");
                        String cname = sc.nextLine();

                        System.out.print("Grade: ");
                        String grade = sc.nextLine();

                        student.addCourse(new Course(cname, "", grade));
                    }

                    sms.addStudent(student);
                    break;

                case 2:
                    sms.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    String delId = sc.nextLine();

                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {
                        sms.deleteStudentById(delId);
                    } else {
                        System.out.println("❌ Delete cancelled.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    String sid = sc.nextLine();

                    System.out.print("Enter Course Name to delete: ");
                    String course = sc.nextLine();

                    sms.deleteCourse(sid, course);
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
