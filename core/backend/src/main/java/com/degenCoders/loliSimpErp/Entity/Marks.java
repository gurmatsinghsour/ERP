package com.degenCoders.loliSimpErp.Entity;
@Entity
@Table(name = "marks")

public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course courseId;

    @Column(name = "assessment", nullable = false)
    private String assessment;

    @Column(name = "score", nullable = false, precision = 5, scale = 2)
    private BigDecimal score;

    @Column(name = "max_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxScore;

    @Column(name = "scored_at", nullable = false)
    private LocalDate scoredAt;
    
    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Student getStudentId() {
        return this.studentId;
    }

    public void setStudent(Student student) {
        this.studentId = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }

    public LocalDate getScoredAt() {
        return scoredAt;
    }

    public void setScoredAt(LocalDate scoredAt) {
        this.scoredAt = scoredAt;
    }

}
