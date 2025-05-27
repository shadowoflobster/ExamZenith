package com.ExamZenith.ExamZenith.courses.model.UserQuestionAnswers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuestionAnswersRequest {
    private Long studentId;
    private Long questionId;
    private Long answerOptionId;
    private String answerText;
}
