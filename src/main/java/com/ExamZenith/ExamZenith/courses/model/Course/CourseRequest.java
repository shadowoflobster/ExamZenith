package com.ExamZenith.ExamZenith.courses.model.Course;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseRequest {
    @Size(min=1, max=200)
    private String name;
}
