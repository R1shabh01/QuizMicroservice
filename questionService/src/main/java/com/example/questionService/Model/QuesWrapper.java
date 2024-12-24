package com.example.questionService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuesWrapper {

    private int quesNo;
    private String question;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;

}
