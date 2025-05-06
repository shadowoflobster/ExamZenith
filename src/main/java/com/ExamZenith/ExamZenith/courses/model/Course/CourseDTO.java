package com.ExamZenith.ExamZenith.courses.model.Course;


import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CourseDTO {
        private Long id;
        private String name;
        private Set<QuestionFormDTO> questionFormDTOset = new HashSet<>();

        public CourseDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
