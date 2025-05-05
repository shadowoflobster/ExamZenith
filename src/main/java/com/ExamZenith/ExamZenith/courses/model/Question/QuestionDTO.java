package com.ExamZenith.ExamZenith.courses.model.Question;

import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionDTO;
import com.ExamZenith.ExamZenith.courses.model.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private Long question_form_id;
    private String question_text;
    private QuestionType question_type;
    private Set<AnswerOptionDTO> answerOptionDTOSet;
}
