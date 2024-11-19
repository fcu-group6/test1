// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.*;

public class User {
    String username;
    String password;
    // Initialize personal enrolledCourses
    List<Course> enrolledCourses = new ArrayList<>();

    User(String username, String password, List<Course> requiredCourse) {
        this.username = username;
        this.password = password;
        if (requiredCourse != null) 
        {
            this.enrolledCourses.addAll(requiredCourse);
        }

        // Initial courses with syllabus information
        // Syllabus syllabus1 = new Syllabus("Computer Science", "Undergraduate", "CS101", "Mon 10-12", "Introduction to Computer Science", "required", "Dr. Wang", "Introduction to basic computer science concepts.");
        // enrolledCourses.add(new Course("CS101", "Introduction to Computer Science", "Dr. Wang", 20, 70, 70, 102, 104, "required", syllabus1));
    }

    public int getTotalCredits() {
        int totalCredits = 0;
        for (Course course : enrolledCourses) {
            totalCredits += course.credits;
        }
        return totalCredits;
    }

    // Method to check if the user is already enrolled in a specific course by course ID
    public boolean isEnrolled(String courseId) {
        for (Course course : enrolledCourses) {
            if (course.id.equalsIgnoreCase(courseId)) {
                return true;
            }
        }
        return false;
    }

    // Method to enroll in a new course
    public void enrollInCourse(Course course) {
        if (!isEnrolled(course.id)) {
            enrolledCourses.add(course);
            System.out.println("Successfully enrolled in course: " + course.name);
        } else {
            System.out.println("Already enrolled in course: " + course.name);
        }
    }

    // Display all enrolled courses and their details
    public void displayEnrolledCourses() {
        System.out.println("Enrolled Courses:");
        for (Course course : enrolledCourses) {
            System.out.println("Course: " + course.name + ", ID: " + course.id + ", Instructor: " + course.instructor + ", Credits: " + course.credits);
            System.out.println("Quota: " + course.quota + ", Rest quota: " + course.restquota + ", Begin(day/lesson): " + course.b_time + ", End(day/lesson): " + course.e_time);
            if (course.syllabus != null) {
                course.syllabus.displaySyllabus(); // Display syllabus details if available
            }
        }
    }
}

