public class Syllabus {
    private String department; 
    private String level;   
    private String courseCode;  
    private String schedule;        
    private String courseName;      
    private String courseCategory;  
    private String instructor;      
    private String courseOutline;   

    public Syllabus(String department, String level, String courseCode, String schedule, String courseName,
                    String courseCategory, String instructor, String courseOutline) {
        this.department = department;
        this.level = level;
        this.courseCode = courseCode;
        this.schedule = schedule;
        this.courseName = courseName;
        this.courseCategory = courseCategory;
        this.instructor = instructor;
        this.courseOutline = courseOutline;
    }

    public void displaySyllabus() {
        System.out.println("depaertment: " + department);
        System.out.println("level: " + level);
        System.out.println("CourseID: " + courseCode);
        System.out.println("CourseTime: " + schedule);
        System.out.println("CourseName " + courseName);
        System.out.println("CourseCategory" + courseCategory);
        System.out.println("COurseInstrcut" + instructor);
        System.out.println("CountSyllabus" + courseOutline);
    }
    public String getCourseCategory() {
        return courseCategory;
    }
}
