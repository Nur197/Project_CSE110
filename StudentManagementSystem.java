import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {

    /* =========================
       ADD STUDENT (INSERT)
       ========================= */
    public void addStudent(Student student) {

        String sql = """
            INSERT INTO students
            (student_name, student_id, date_of_birth, course, grade)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (Course c : student.getCourses()) {
                if (c == null) continue;

                ps.setString(1, student.getName());
                ps.setString(2, student.getId());
                ps.setDate(3, Date.valueOf(student.getDob()));
                ps.setString(4, c.getCourseName());
                ps.setString(5, c.getGrade());

                ps.executeUpdate();
            }

            System.out.println("✅ Student data inserted successfully!");

        } catch (SQLException e) {
            System.out.println("❌ Insert error: " + e.getMessage());
        }
    }

    /* =========================
       READ ALL STUDENTS
       ========================= */
    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY student_id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Student currentStudent = null;
            String lastId = "";

            while (rs.next()) {

                String name = rs.getString("student_name");
                String id = rs.getString("student_id");
                String dob = rs.getDate("date_of_birth").toString();
                String course = rs.getString("course");
                String grade = rs.getString("grade");

                if (!id.equals(lastId)) {
                    currentStudent = new Student(name, id, dob);
                    list.add(currentStudent);
                    lastId = id;
                }

                currentStudent.addCourse(new Course(course, "", grade));
            }

        } catch (SQLException e) {
            System.out.println("❌ Read error: " + e.getMessage());
        }

        return list;
    }

    /* =========================
       DISPLAY STUDENTS
       ========================= */
    public void displayStudents() {

        List<Student> students = getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            System.out.println("\nName: " + s.getName());
            System.out.println("ID: " + s.getId());
            System.out.println("DOB: " + s.getDob());
            System.out.println("Courses:");

            boolean hasCourse = false;
            for (Course c : s.getCourses()) {
                if (c != null) {
                    hasCourse = true;
                    System.out.println(" - " + c.getCourseName()
                            + " (Grade: " + c.getGrade() + ")");
                }
            }

            if (!hasCourse) {
                System.out.println(" - None");
            }
        }
    }

    /* =========================
       DELETE FULL STUDENT
       ========================= */
    public void deleteStudentById(String studentId) {

        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student deleted successfully!");
            } else {
                System.out.println("❌ No student found with this ID.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Delete error: " + e.getMessage());
        }
    }

    /* =========================
       DELETE SPECIFIC COURSE
       ========================= */
    public void deleteCourse(String studentId, String courseName) {

        String sql = "DELETE FROM students WHERE student_id = ? AND course = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ps.setString(2, courseName);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Course deleted successfully!");
            } else {
                System.out.println("❌ Course not found.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Delete error: " + e.getMessage());
        }
    }
}
