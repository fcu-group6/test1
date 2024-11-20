// Source code is decompiled from a .class file using FernFlower decompiler.
 public class Course {
   String id;
   String name;
   String instructor;
   String attr;
   int credits;
   int quota;
   int restquota;
   int b_time, e_time;
   Syllabus syllabus; // 引用 Syllabus 物件

   public Course(String id, String name, String instructor, int credits, int quota, int restquota, 
                 int b_time, int e_time, String attr, Syllabus syllabus) {
       this.id = id;
       this.name = name;
       this.instructor = instructor;
       this.credits = credits;
       this.quota = quota;
       this.restquota = restquota;
       this.b_time = b_time;
       this.e_time = e_time;
       this.attr = attr;
       this.syllabus = syllabus;
   }

   public void displaySyllabus() {
       if (syllabus != null) {
           syllabus.displaySyllabus();
       } else {
           System.out.println("No Found CourseSyllabus。");
       }
   }
}

 