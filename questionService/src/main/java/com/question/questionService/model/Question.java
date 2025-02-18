package com.question.questionService.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="question")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String questionTitle;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String correctAns;

    private String difficultyLevel;

    private String category;
}
