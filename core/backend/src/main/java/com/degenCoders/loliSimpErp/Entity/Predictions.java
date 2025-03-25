package com.degenCoders.loliSimpErp.Entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "predictions")
public class Predictions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prediction_id")
    private Long predictionId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student studentId;

    @Column(name = "term", nullable = false)
    private Short term;

    @Column(name = "predicted_outcome", nullable = false, length = 10)
    private String predictedOutcome;

    @Column(name = "probability", nullable = false, precision = 4, scale = 3)
    private Double probability;

    @ManyToOne
    @JoinColumn(name = "model_version_id", nullable = false)
    private ModelHistory modelVersionId;

    @Column(name = "predicted_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private Timestamp predictedAt;

    public Long getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(Long predictionId) {
        this.predictionId = predictionId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public ModelHistory getModelVersionId() {
        return modelVersionId;
    }

    public void setModelVersionId(ModelHistory modelVersionId) {
        this.modelVersionId = modelVersionId;
    }

    public Timestamp getPredictedAt() {
        return predictedAt;
    }

    public void setPredictedAt(Timestamp predictedAt) {
        this.predictedAt = predictedAt;
    }
}
