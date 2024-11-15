import java.util.*;
// AddCourse class
class AddCourse implements CourseOperation 
{
    private List<Course> courses;
    private User[] currentUser;
    private Scanner scanner;
    private int flag = 0;
    private int totalCredits = 0;

    AddCourse(List<Course> courses, User[] currentUser, Scanner scanner) 
    {
        this.courses = courses;
        this.currentUser = currentUser;
        this.scanner = scanner;
    }
    
    @Override
    public void execute() 
    {
        if (currentUser[0] == null) 
        {
            System.out.println("Please login first!");
            return;
        }

        System.out.print("Enter course ID to enroll: ");
        String courseId = scanner.nextLine().toLowerCase();
    
        for(Course exist : currentUser[0].enrolledCourses)
        {
            totalCredits += exist.credits;
        }
        for (Course course : courses) 
        {
            if (course.id.toLowerCase().equals(courseId)) 
            {
                for(Course exist : currentUser[0].enrolledCourses)
                {
                    if (exist == course) 
                    {
                        System.out.println("You have been enrolled this course.Please check your personal courses.");    
                        return;        
                    }
                    if (totalCredits + course.credits > 25) 
                    {
                        System.out.println("You credits is full.(>25).");    
                        return;
                    }
                    if (course.restquota > 0 ) {
                        course.restquota--;
                    }
                    else
                    {
                        System.out.println("This course does not have enough quota.");
                        return;
                    }
                    if (exist.b_time >= course.b_time) 
                    {
                        if (exist.b_time <= course.e_time) 
                        {
                            flag = 1;
                        }
                    }
                    else
                    {
                        if (exist.e_time <= course.e_time && exist.e_time >= course.b_time) 
                        {
                            flag = 1;
                        }
                    }
                }
                if(flag == 0)
                {
                    currentUser[0].enrolledCourses.add(course);
                    System.out.println("Enrolled in " + course.name);
                    return;
                }
                else
                {
                    System.out.println("Course can not enroll!(you had been choose the class in this time interval)");
                    System.out.println("Please go to->viewCourse->Delete course,then you can choose another course by this time interval.");    
                    flag = 0;
                    return;        
                }
            }
        }
        System.out.println("Course not found!");
    }
}