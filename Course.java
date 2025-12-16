public class Course {
    private String courseName;
    private String courseCode; // optional (not used in DB table)
    private String grade;

    public Course(String courseName, String courseCode, String grade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return courseName + " - Grade: " + grade;
    }
}

