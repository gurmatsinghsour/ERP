package com.degenCoders.loliSimpErp.Entity;
@Entity
@Table(name = "attendance")
public class Attendance {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id", nullable=false)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable= false)
    private Course course;

    @Column(name = "class_date", nullable=false)
    private Date classDate;

    @Column(name = "status", nullable=false)
    private String status;


    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Student getstudent() {
        return student;
    }

    public void setstudent(Student student) {
        this.student = student;
    }

    public Course getcourse() {
        return course;
    }

    public void setcourse(Course course) {
        this.course = course;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
