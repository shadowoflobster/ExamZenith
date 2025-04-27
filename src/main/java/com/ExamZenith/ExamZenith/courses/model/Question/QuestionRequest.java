package com.ExamZenith.ExamZenith.courses.model.Question;


import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionRequest;
import com.ExamZenith.ExamZenith.courses.model.QuestionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionRequest {
    private Long question_form_id;
    private String question_text;
    private QuestionType question_type;
    private List<AnswerOptionRequest> options;
}
