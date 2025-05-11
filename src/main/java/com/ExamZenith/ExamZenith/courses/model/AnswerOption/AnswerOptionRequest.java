package com.ExamZenith.ExamZenith.courses.model.AnswerOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerOptionRequest {
    private Long question_id;
    private String option_text;

}
