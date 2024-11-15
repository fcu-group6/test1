import java.util.*;
// CourseSystem class to manage the operations
public class CourseSystem {
    private Map<String, User> users = new HashMap<>();
    private List<Course> courses = new ArrayList<>();
    private User[] currentUser = new User[1];
    private Scanner scanner;

    public CourseSystem() 
    {
        this.scanner = new Scanner(System.in); // Initialize the single Scanner instance
        initializeCourses();
        initializeUsers();
    }

    // Initialize the list of courses
    private void initializeCourses() 
    {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Dr. Wang", 3, 70, 70, 102,104, "required"));
        courses.add(new Course("CS102", "Data Structures", "Dr. Lee", 3, 70, 0,201,203, "elective"));
        courses.add(new Course("CS103", "Algorithms", "Dr. Chang", 3, 60, 5, 405,408,"elective"));
        courses.add(new Course("CS104", "Operating Systems", "Dr. Huang", 2, 55, 15,302,303,"elective"));
        courses.add(new Course("CS105", "Computer Networks", "Dr. Lin", 2, 55, 20,302,303,"elective"));
        courses.add(new Course("CS106", "Database Systems", "Dr. Yang", 2, 70, 15,301,302,"elective"));
        courses.add(new Course("CS107", "Machine Learning", "Dr. Chen", 2, 68, 17,303,304,"elective"));
        courses.add(new Course("CS108", "Machine Learning", "Dr. Liu", 2, 68, 10,503,504,"elective"));
    }

    // Initialize the list of users
    private void initializeUsers() 
    {
        users.put("u1", new User("user1", "p1"));
        users.put("user2", new User("user2", "password2"));
    }

    // Main run method to display the menu and handle user input
    public void run()
    {
        Login login = new Login(users, currentUser, scanner); // Pass scanner to Login
        AddCourse addCourse = new AddCourse(courses, currentUser, scanner);
        DeleteCourse deleteCourse = new DeleteCourse(currentUser, scanner, courses);
        SearchCourse searchCourse = new SearchCourse(courses, scanner,currentUser, addCourse);
        ViewCourse viewCourse = new ViewCourse(courses, currentUser, scanner, deleteCourse);

        int choice = -1; // Initialize choice variable
        do {
            System.out.println("\n1. Login\n2. Logout\n3. Search Course\n4. View Enrolled Courses\n5. View All Courses\n6. Exit\nChoose an option: ");
            if (scanner.hasNextInt()) 
            {
                choice = scanner.nextInt(); // Assign input value to choice
                scanner.nextLine(); // Consume newline after nextInt to prevent skipping input in execute

                switch (choice) {
                    case 1 -> login.execute();
                    case 2 -> login.logout();
                    // case 3 -> addCourse.execute();
                    // case 4 -> deleteCourse.execute();
                    case 3 -> searchCourse.execute();
                    case 4 -> viewCourse.execute();
                    case 5 -> viewCourse.viewAllCourses();
                    case 6 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid option. Please choose a valid option.");
                }
            } 
            else 
            {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        } while (choice != 6);

        scanner.close(); // Close the scanner after loop ends
    }

    public static void main(String[] args) 
    {
        CourseSystem system = new CourseSystem();
        system.run();
    }
}
