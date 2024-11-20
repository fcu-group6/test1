import java.util.*;

// AddCourse class
class AddCourse implements CourseOperation {
    private List<Course> courses;
    private User[] currentUser;
    private Scanner scanner;
    private int flag = 0;
    private int totalCredits = 0;

    AddCourse(List<Course> courses, User[] currentUser, Scanner scanner) {
        this.courses = courses;
        this.currentUser = currentUser;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (currentUser[0] == null) {
            System.out.println("Please login first!");
            return;
        }

        System.out.print("Enter course ID to enroll: ");
        String courseId = scanner.nextLine().trim(); // 去除多餘空格，保留原始格式

        enrollInCourse(courseId); // 呼叫新的加選方法
    }

    // 新增 enrollInCourse 方法，供 SearchCourse 類別直接調用
    public void enrollInCourse(String courseId) {
        totalCredits = 0; // 每次執行重新計算學分
        for (Course exist : currentUser[0].enrolledCourses) {
            totalCredits += exist.credits;
        }

        for (Course course : courses) {
            // 修改為忽略大小寫的比較
            if (course.id.equalsIgnoreCase(courseId)) {
                // 檢查課程是否為必修
                if ("required".equalsIgnoreCase(course.attr)) {
                    System.out.println("The course is required. No need to add it manually.");
                    return;
                }

                // 檢查是否已選過此課程
                for (Course exist : currentUser[0].enrolledCourses) {
                    if (exist == course) {
                        System.out.println("You have already enrolled in this course. Please check your enrolled courses.");
                        return;
                    }

                    // 檢查學分是否超過上限
                    if (totalCredits + course.credits > 25) {
                        System.out.println("Your total credits exceed the maximum allowed (25).");
                        return;
                    }

                    // 檢查課程剩餘名額
                    if (course.restquota <= 0) {
                       
                        System.out.println("This course does not have enough quota.");
                        return;
                    } 
                    
                    // 檢查是否衝堂
                    if (timeConflict(exist, course)) {
                        flag = 1;
                    }
                }

                // 確認可以選課
                if (flag == 0) {
                    currentUser[0].enrolledCourses.add(course);
                    course.restquota--;
                    System.out.println("Successfully enrolled in " + course.name);
                } else {
                    System.out.println("Course enrollment failed due to a schedule conflict.");
                    System.out.println("Please delete the conflicting course in the 'View Course' menu, then try again.");
                    flag = 0;
                }
                return;
            }
        }
        System.out.println("Course not found!");
    }

    // 簡化的時間衝突檢查方法
    private boolean timeConflict(Course exist, Course course) {
        return (exist.b_time >= course.b_time && exist.b_time <= course.e_time) ||
               (exist.e_time <= course.e_time && exist.e_time >= course.b_time);
    }
}
