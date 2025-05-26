package com.ExamZenith.ExamZenith.courses.model.UserQuestionAnswers;


import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserQuestionAnswersDTO {
    private Long studentId;
    private Long questionId;
    private Long answerOptionId;
    private String answerText;
    private LocalDateTime answeredAt;

}
