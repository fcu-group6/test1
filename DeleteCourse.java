import java.util.*;
// DeleteCourse class
class DeleteCourse implements CourseOperation {
    private User[] currentUser;
    private Scanner scanner;
    private List<Course> courses;

    DeleteCourse(User[] currentUser, Scanner scanner, List<Course> courses) 
    {
        this.currentUser = currentUser;
        this.scanner = scanner;
        this.courses = courses;
    }

    @Override
    public void execute() 
    {
        if (currentUser[0] == null) 
        {
            System.out.println("Please login first!");
            return;
        }

        System.out.print("Enter course ID to remove: ");
        String courseId = scanner.nextLine().toLowerCase();
        
        boolean courseExists = courses.stream().anyMatch(course -> course.id.toLowerCase().equals(courseId));
        if (!courseExists) 
        {
            System.out.println("This class doesn't exist");
            return;
        }

        boolean found = false;
        for (Course course : currentUser[0].enrolledCourses) 
        {
            if (course.id.toLowerCase().equals(courseId)) 
            {
                if (course.attr == "required") 
                {
                    System.out.println("Required course can not be removed.");
                    found = true;
                    return;
                }
                currentUser[0].enrolledCourses.remove(course);
                course.restquota++;
                System.out.println("Course removed.");
                found = true;
                break;
            }
        }

        if (!found) 
        {
            System.out.println("You didn't enroll this class");
            return;
        }
        
    }
}