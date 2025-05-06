package com.ExamZenith.ExamZenith.courses.model.QuestionForm;

import com.ExamZenith.ExamZenith.courses.model.Question.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionFormDTO {
    private Long id;
    private Long course_id;
    private String title;
    private Set<QuestionDTO> questionDTOSet;


}
