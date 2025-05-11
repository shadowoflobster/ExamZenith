package com.ExamZenith.ExamZenith.courses.model.QuestionForm;

import com.ExamZenith.ExamZenith.courses.model.Question.QuestionRequest;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionFormRequest {
    @Size(max = 200)
    private String title;
    private Long course_id;
    private Set<QuestionRequest> questions = new HashSet<>();
}
