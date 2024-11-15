import java.util.*;
// SearchCourse class
class SearchCourse implements CourseOperation 
{
    private List<Course> courses;
    private User[] currentUser;
    private Scanner scanner;
    private AddCourse add;
    private int choice = -1;
    private int total = 0;

    SearchCourse(List<Course> courses, Scanner scanner,User[] currentUser, AddCourse add) 
    {
        this.courses = courses;
        this.scanner = scanner;
        this.currentUser = currentUser;
        this.add = add;
    }

    @Override
    public void execute() 
    {
        if (currentUser[0] == null) 
        {
            System.out.println("Please login first!");
            return;
        }

        System.out.print("Enter course ID or course name to search: ");
        String target = scanner.nextLine().toLowerCase();
        
        boolean courseFound = false; // Flag to check if course is found
        for (Course course : courses) 
        {
            while(course.id.toLowerCase().contains(target) || course.name.toLowerCase().contains(target)) 
            {
                total++;
                System.out.println("Course found: " + course.name + ", ID: " + course.id + ", Instructor: " + course.instructor + ", Credits: " + course.credits + ", Quota: " + course.quota + ", Rest quota: " + course.restquota +", Begin(day/lesson): " + course.b_time+", End(day/lesson): " + course.e_time);
                courseFound = true;
                break; // Stop searching once the course is found
            }
        }
        System.out.println("Total courses:"+total);

        if (!courseFound) {
            System.out.println("Course not found!"); // Display message if no course matched
            return;
        }

        System.out.println("Enroll course input:1, exit search input:0 ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline after nextInt

        if (choice == 1) 
        {
            add.execute(); // Enroll in the course
        } 
        else 
        {
            System.out.println("Exiting search.");
            return;
        }
    }
}
