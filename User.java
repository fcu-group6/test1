// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.*;

public class User {
   String username;
   String password;
   // initialize personal enrolledCourses
   List<Course> enrolledCourses = new ArrayList<>();

   User(String var1, String var2) 
   {
        // without required course
        this.username = var1;
        this.password = var2;

        //initial courses
        enrolledCourses.add(new Course("CS101", "Introduction to Computer Science", "Dr. Wang", 3, 70, 70, 102,104, "required"));
   }
   public int getTotalCredits() 
   {
      int totalCredits = 0;
      for (Course course : enrolledCourses) {
          totalCredits += course.credits;
      }
      return totalCredits;
   }
}
