// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Course {
    private String courseNumber;
    private String descriptiveTitle;
    private double units;
    private String term;
    private String yearLevel;
    private double grade;
    
    public Course(){
    courseNumber = "CS000";
    descriptiveTitle = "Course";
    units = 0;
    term = "1st Semester";
    yearLevel = "1st Year";
    grade = 75.0;
        
    }//end of Course constructor
    
    public Course(String c, String d, double u, String t, String y, double g){
     courseNumber = c;
    descriptiveTitle = d;
    units = u;
    term = t;
    yearLevel = y;
    grade = g;
    }
    
    public String getCourseNumber(){
        return courseNumber;
    }
    
     public String getDescriptiveTitle(){
        return descriptiveTitle;
    }
    
     public double getUnits(){
        return units;
    }
    
     public String getTerm(){
        return term;
    }
    
     public String getYearLevel(){
        return yearLevel;
    }
    
    public double getGrade(){
        return grade;
    }
    
    public void setCourseNumber(String CourseNumber){
        courseNumber = CourseNumber;
    }
    
    public void setDescriptiveTitle(String DescriptiveTitle){
        descriptiveTitle = DescriptiveTitle;
    }
    public void setUnits(double Units){
        units = Units;
    }
    
    public void setTerm(String Term){
        term = Term;
    }
    
    public void setYearLevel(String YearLevel){
        yearLevel = YearLevel;
    }
    
    public void setGrade (double Grade){
        grade = Grade;
    }

    public String toString(){
        return System.out.print(courseNumber+", "+descriptiveTitle+", "+units+", "+grade+", "+yearLevel+", "+term)
    }
        
    
}//end of Course class
