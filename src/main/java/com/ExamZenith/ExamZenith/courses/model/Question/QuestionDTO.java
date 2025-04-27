package com.ExamZenith.ExamZenith.courses.model.Question;

import com.ExamZenith.ExamZenith.courses.model.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private Long question_form_id;
    private String question_text;
    private QuestionType question_type;;
}
