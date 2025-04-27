package com.ExamZenith.ExamZenith.courses.model.AnswerOption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerOptionRequest {

    private Long question_id;
    private String option_text;

}
