import java.util.*; 

// SearchCourse class
class SearchCourse implements CourseOperation {
    private List<Course> courses;
    private User[] currentUser;
    private Scanner scanner;
    private AddCourse add;
    private int choice = -1;
    private int total = 0;

    SearchCourse(List<Course> courses, Scanner scanner, User[] currentUser, AddCourse add) {
        this.courses = courses;
        this.scanner = scanner;
        this.currentUser = currentUser;
        this.add = add;
    }

    @Override
    public void execute() {
        if (currentUser[0] == null) {
            System.out.println("Please login first!");
            return;
        }

        do {
            System.out.println("\nSelect an option:\n1. Return to Main Menu\n2. Search by course name or ID\n3. View course syllabus");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
                case 2 -> {
                    searchByCourseNameOrID();

                    if(total!=0)
                    {
                    
                        System.out.print("Do you want to enroll in a course? (1: Yes, 0: No): ");
                        String enrollChoice = scanner.nextLine();
                        if ("1".equals(enrollChoice)) {
                            System.out.print("Enter course ID to enroll: ");
                            String courseId = scanner.nextLine();
                            add.enrollInCourse(courseId); // 呼叫 enrollInCourse 方法進行加選
                        } else if (!"0".equals(enrollChoice)) {
                            System.out.println("Invalid choice. Please enter 1 or 0.");
                        }
                    }
                }
                case 3 -> viewCourseSyllabus();
                default -> System.out.println("Invalid option. Please select a valid option.");
            }
        } while (choice != 1);
    }

    private void searchByCourseNameOrID() {
        System.out.print("Enter course ID or course name to search: ");
        String target = scanner.nextLine().toLowerCase();

        total = 0;
        for (Course course : courses) {
            if (course.id.toLowerCase().contains(target) || course.name.toLowerCase().contains(target)) {
                total++;
                System.out.println("Course found: " + course.name + ", ID: " + course.id + ", Instructor: " + course.instructor + ", Credits: " + course.credits + ", Quota: " + course.quota + ", Rest quota: " + course.restquota + ", Begin(day/lesson): " + course.b_time + ", End(day/lesson): " + course.e_time);
            }
        }

        System.out.println("Total matching courses: " + total);

        if (total == 0) {
            System.out.println("No matching courses found.");
        }
    }

    private void viewCourseSyllabus() {
        System.out.print("Enter course ID (5-digit format): ");
        String target = scanner.nextLine().toUpperCase();

        boolean courseFound = false;
        for (Course course : courses) {
            if (course.id.equals(target)) {
                course.displaySyllabus();
                courseFound = true;
                break;
            }
        }

        if (!courseFound) {
            System.out.println("Course not found!");
        }
    }
}
