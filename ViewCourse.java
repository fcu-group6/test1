import java.util.*;

// ViewCourse class
class ViewCourse implements CourseOperation 
{
    private List<Course> courses;
    private User[] currentUser;
    private Scanner scanner;
    private DeleteCourse del;
    private int choice = -1;
    private int totalCredits = 0;

    ViewCourse(List<Course> courses, User[] currentUser,Scanner scanner, DeleteCourse del) 
    {
        this.courses = courses;
        this.currentUser = currentUser;
        this.scanner = scanner;
        this.del = del;
    }

    @Override
    public void execute() 
    {
        if (currentUser[0] == null) 
        {
            System.out.println("Please login first!");
            return;
        }

        while (true) 
        {
            currentUser[0].enrolledCourses.sort(Comparator.comparingInt(course -> course.b_time));
            totalCredits = 0;
            for (Course course : currentUser[0].enrolledCourses) 
            {
                totalCredits += course.credits;
            }

            System.out.println("Enrolled Courses:");
            for (Course course : currentUser[0].enrolledCourses) 
            {
                System.out.println(" - " + course.name + " (" + course.id + " begin: " + course.b_time + " end:"+ course.e_time +  " attr: " + course.attr + ")");
            }
            System.out.println("Total credits:" + totalCredits);

            System.out.println("Delete course input:1, exit view input:0 ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline after nextInt
            if (choice == 1) 
            {
                del.execute(); // Enroll in the course
                System.out.println();
            } 
            else 
            {
                System.out.println("Exiting view.");
                break;
            }
        }
    }

    public void viewAllCourses() 
    {
        if (currentUser[0] == null) 
        {
            System.out.println("Please login first!");
            return;
        }
        courses.sort(Comparator.comparingInt(course -> course.b_time));
        System.out.println("Available Courses:");
        for (Course course : courses) 
        {
            System.out.println(course.id + ": " + course.name + " by " + course.instructor + " Quota " + course.quota + " Rest Quota " + course.restquota + " (" + course.credits + " credits)"+ " begin: " + course.b_time + " end:"+ course.e_time + " attr:" + course.attr);
        }
    }
}
