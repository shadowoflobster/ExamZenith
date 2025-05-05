package com.ExamZenith.ExamZenith.courses.model.Question;


import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionRequest;
import com.ExamZenith.ExamZenith.courses.model.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionRequest {
    @NotNull
    private Long question_form_id;
    @NotBlank
    private String question_text;
    @NotNull
    private QuestionType question_type;
    private List<AnswerOptionRequest> options;
}
