import java.util.*;

// CourseSystem class to manage the operations
public class CourseSystem {
    private Map<String, User> users = new HashMap<>();
    private List<Course> courses = new ArrayList<>();
    private User[] currentUser = new User[1];
    private Scanner scanner;

    public CourseSystem() {
        this.scanner = new Scanner(System.in); // Initialize the single Scanner instance
        initializeCourses();
        initializeUsers();
    }

    // Initialize the list of courses with syllabus information
    private void initializeCourses() {
        // Initialize syllabus for each course
        Syllabus syllabus1 = new Syllabus(
            "Computer Science",
            "Undergraduate",
            "CS101",
            "Mon 10-12",
            "Introduction to Computer Science",
            "required",
            "Dr. Wang",
            "week1-3: Introduction to basic computer science concepts.\n" +
            "week4-6: Test concepts and knowledge.\n" +
            "week7-10: Advanced topics in CS.\n" +
            "week11-15: Project work.\n" +
            "week16: Final test."
        );
        Syllabus syllabus2 = new Syllabus(
            "Computer Science", 
            "Undergraduate", 
            "CS102", 
            "Tue 14-16", 
            "Data Structures",
            "elective", 
            "Dr. Lee",
            "week1-3: Study of fundamental data structures.\n "+
            "week4-6:Test concepts and knowledge.\n " +
            "week7-10: Advanced topics in CS.\n "+
            "week11-15: Project work.\n" +
            "week16: Final test."
        );
        Syllabus syllabus3 = new Syllabus(
            "Algorithms", 
            "Undergraduate", 
            "CS103", 
            "Wed 9-11",
            "Algorithms", 
            "required", 
            "Dr. Chang",
            "week1-3: Introduction to algorithms and problem-solving techniques.\n "+
            "week4-6: concepts and knowledge.\n " +
            "week7-10: Advanced topics in CS.\n "+
            "week11-15: Project work.\n" +
            "week16: Final test."
        );
        Syllabus syllabus4 = new Syllabus(
            "Operating Systems", 
            "Undergraduate", 
            "CS104", 
            "Thu 11-13", 
            "Operating Systems", 
            "elective", 
            "Dr. Huang", 
            "week1-3: Operating system concepts and design.\n"+
            "week4-6: Fort time tests.\n " +
            "week7-10: Online tests.\n "+
            "week11-15: Project work.\n" +
            "week16: Final test."
            );
        Syllabus syllabus5 = new Syllabus(
            "Computer Networks", 
            "Undergraduate", 
            "CS105",
            "Fri 13-15", 
            "Computer Networks", 
            "elective", 
            "Dr. Lin", 
            "Introduction to network structures and protocols."
         );
        Syllabus syllabus6 = new Syllabus(
            "Database Systems", 
            "Undergraduate", 
            "CS106", 
            "Mon 13-15",
             "Database Systems", 
             "elective", 
             "Dr. Yang", 
             "Introduction to database concepts and SQL.");
        Syllabus syllabus7 = new Syllabus(
            "Machine Learning", 
            "Undergraduate", 
            "CS107",
            "Wed 10-12", 
            "Machine Learning",
            "elective",
            "Dr. Chen", 
            "Introduction to machine learning concepts.");
        Syllabus syllabus8 = new Syllabus(
            "Machine Learning",
            "Undergraduate",
            "CS108",
            "Thu 9-11",
            "Machine Learning",
            "elective",
            "Dr. Liu",
            "Advanced concepts in machine learning.");

        // Add courses with syllabus
        courses.add(new Course("CS101", "Introduction to Computer Science", "Dr. Wang", 20, 70, 70, 102, 104, "required", syllabus1));
        courses.add(new Course("CS102", "Data Structures", "Dr. Lee", 3, 70, 0, 201, 203, "elective", syllabus2));
        courses.add(new Course("CS103", "Algorithms", "Dr. Chang", 3, 60, 5, 405, 408, "elective", syllabus3));
        courses.add(new Course("CS104", "Operating Systems", "Dr. Huang", 2, 55, 15, 302, 303, "elective", syllabus4));
        courses.add(new Course("CS105", "Computer Networks", "Dr. Lin", 2, 55, 20, 302, 303, "elective", syllabus5));
        courses.add(new Course("CS106", "Database Systems", "Dr. Yang", 2, 70, 15, 301, 302, "elective", syllabus6));
        courses.add(new Course("CS107", "Machine Learning", "Dr. Chen", 2, 68, 17, 303, 304, "elective", syllabus7));
        courses.add(new Course("CS108", "Machine Learning", "Dr. Liu", 2, 68, 10, 503, 504, "elective", syllabus8));
    }

    // Initialize the list of users
    private void initializeUsers() {
        List<Course> requiredCourses = new ArrayList<>();
        for(Course course : courses)
        {
            if ("required".equals(course.attr)) 
            {
                requiredCourses.add(course);   
            }
        }

        users.put("u1", new User("user1", "p1", requiredCourses));
        users.put("user2", new User("user2", "password2", requiredCourses));
    }

    // Main run method to display the menu and handle user input
    public void run() {
        Login login = new Login(users, currentUser, scanner); // Pass scanner to Login
        AddCourse addCourse = new AddCourse(courses, currentUser, scanner);
        DeleteCourse deleteCourse = new DeleteCourse(currentUser, scanner, courses);
        SearchCourse searchCourse = new SearchCourse(courses, scanner, currentUser, addCourse);
        ViewCourse viewCourse = new ViewCourse(courses, currentUser, scanner, deleteCourse);

        int choice = -1; // Initialize choice variable
        do {
            System.out.println("\n1. Login\n2. Logout\n3. Search Course\n4. View Enrolled Courses\n5. View All Courses\n6. Exit\nChoose an option: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt(); // Assign input value to choice
                scanner.nextLine(); // Consume newline after nextInt to prevent skipping input in execute

                switch (choice) {
                    case 1 -> login.execute();
                    case 2 -> login.logout();
                    case 3 -> searchCourse.execute();
                    case 4 -> viewCourse.execute();
                    case 5 -> viewCourse.viewAllCourses();
                    case 6 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid option. Please choose a valid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        } while (choice != 6);

        scanner.close(); // Close the scanner after loop ends
    }

    public static void main(String[] args) {
        CourseSystem system = new CourseSystem();
        system.run();
    }
}
