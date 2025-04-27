package com.ExamZenith.ExamZenith.courses.model.AnswerOption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerOptionDTO {
    private Long id;
    private Long question_id;
    private String option_text;
}
