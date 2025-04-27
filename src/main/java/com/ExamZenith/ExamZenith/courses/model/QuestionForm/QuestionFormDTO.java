package com.ExamZenith.ExamZenith.courses.model.QuestionForm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionFormDTO {
    private Long id;
    private Long course_id;
    private String title;

}
