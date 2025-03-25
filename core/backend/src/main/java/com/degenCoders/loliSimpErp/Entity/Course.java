package com.degenCoders.loliSimpErp.Entity;
@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer courseId;

    @Column(nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department departmentId;


    private Integer creditHours;
    private short termOffered;


    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }                              
    
    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Department getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCreditHours() {
        return this.creditHours;
    }

    public void setCreditHours(Integer creditHours) {
        this.creditHours = creditHours;
    }

    public short getTermOffered() {
        return this.termOffered;
    }

    public void setTermOffered(short termOffered) {
        this.termOffered = termOffered;
    }

}
