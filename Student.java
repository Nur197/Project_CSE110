public class Student {
    private String name;
    private String id;
    private String dob; 
    private Course[] courses;

    public Student(String name, String id, String dob) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.courses = new Course[10];
    }

    public void addCourse(Course course) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                return;
            }
        }
        System.out.println("⚠ Course limit reached!");
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDob() {
        return dob;
    }

    public Course[] getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nID: " + id + "\nDOB: " + dob;
    }
}

