package com.example.questionService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
public class Ques {
    @Id
    @NotNull
    private int quesNo;
    @NotNull
    private String question;
    @NotNull
    private String opt1;
    @NotNull
    private String opt2;
    @NotNull
    private String opt3;
    @NotNull
    private String opt4;
    @NotNull
    private String answer;
    @NotNull
    private String category;


}
