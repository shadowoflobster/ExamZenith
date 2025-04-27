package com.ExamZenith.ExamZenith.courses.model.QuestionForm;

import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class QuestionFormRequest {
    @Size(min=0, max = 200)
    private String title;
    private Long course_id;
    private Set<Question> questions = new HashSet<>();
}
