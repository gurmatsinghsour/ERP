package com.degenCoders.loliSimpErp.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime; 
import jakarta.persistence.*;

@Entity
@Table(name = "predictions")
public class Predictions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prediction_id")
    private Long predictionId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;    

    @Column(name = "term", nullable = false)
    private Short term;

    @Column(name = "predicted_outcome", nullable = false, length = 10)
    private String predictedOutcome;

    @Column(name = "probability", nullable = false, precision = 4, scale = 3, columnDefinition = "NUMERIC(4,3)")
    private BigDecimal probability;

    @ManyToOne
    @JoinColumn(name = "model_version_id", nullable = false)
    private ModelHistory modelVersionId;

    @Column(name = "predicted_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime predictedAt;

    public Long getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(Long predictionId) {
        this.predictionId = predictionId;
    }

    public Student getStudentId() {
        return this.student;
    }

    public void setStudentId(Student studentId) {
        this.student = studentId;
    }

    public Short getTerm() {
        return term;
    }

    public void setTerm(Short term) {
        this.term = term;
    }

    public String getPredictedOutcome() {
        return predictedOutcome;
    }

    public void setPredictedOutcome(String predictedOutcome) {
        this.predictedOutcome = predictedOutcome;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public ModelHistory getModelVersionId() {
        return modelVersionId;
    }

    public void setModelVersionId(ModelHistory modelVersionId) {
        this.modelVersionId = modelVersionId;
    }

    public LocalDateTime getPredictedAt() {
        return predictedAt;
    }

    public void setPredictedAt(LocalDateTime predictedAt) {
        this.predictedAt = predictedAt;
    }
}
